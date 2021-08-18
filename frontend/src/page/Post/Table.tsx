import React from "react";
import { useAppDispatch, useAppSelector } from "../../store";
import {
  Button,
  CircularProgress,
  Link,
  makeStyles,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
} from "@material-ui/core";
import { useHistory } from "react-router-dom";
import { clearAuthor, getPostsByAuthor } from "./postSlice";

const useStyles = makeStyles({
  img: {
    maxWidth: 200,
    maxHeight: 100,
  },
});

export function PostTable() {
  const data = useAppSelector((state) => state.post.data);
  const request = useAppSelector((state) => state.post.request);
  const author = useAppSelector((state) => state.post.author);
  const classes = useStyles();
  const history = useHistory();
  const dispatch = useAppDispatch();

  return (
    <div>
      {data ? (
        <React.Fragment>
          <div>
            <TablePagination
              count={data.totalElements}
              page={data.number}
              onPageChange={(e, page) => history.push({ state: { page } })}
              onRowsPerPageChange={(e) => history.push({ state: { page: 0, size: e.target.value } })}
              rowsPerPage={request.size}
              size="small"
            />
            {author && (
              <span>
                Author: {author} <Button onClick={() => dispatch(clearAuthor())}>x</Button>
              </span>
            )}
          </div>
          <TableContainer>
            <Table>
              <TableHead>
                <TableCell>ID</TableCell>
                <TableCell>Author</TableCell>
                <TableCell>Created Time</TableCell>
                <TableCell>Body</TableCell>
                <TableCell />
              </TableHead>
              <TableBody>
                {data.content.map((_) => (
                  <TableRow key={_.id}>
                    <TableCell>{_.id}</TableCell>
                    <TableCell>
                      <Link onClick={() => dispatch(getPostsByAuthor(_.authorName))}>{_.authorName}</Link>
                    </TableCell>
                    <TableCell>{_.createdTime}</TableCell>
                    <TableCell>{_.body}</TableCell>
                    <TableCell>
                      <img className={classes.img} src={_.imageUrl} alt={`${_.id} Image`} />
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </React.Fragment>
      ) : (
        <CircularProgress />
      )}
    </div>
  );
}
