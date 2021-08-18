import React from "react";
import { useAppSelector } from "../../store";
import {
  CircularProgress,
  makeStyles,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@material-ui/core";

const useStyles = makeStyles({
  img: {
    maxWidth: 200,
    maxHeight: 100,
  },
});

export function PostTable() {
  const data = useAppSelector((state) => state.post.data);
  const classes = useStyles();

  return (
    <div>
      {data ? (
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
                  <TableCell>{_.authorName}</TableCell>
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
      ) : (
        <CircularProgress />
      )}
    </div>
  );
}
