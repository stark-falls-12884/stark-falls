import React from "react";
import { Link as RouterLink } from "react-router-dom";
import MuiLink, { LinkProps } from "@material-ui/core/Link";

export function Link(props: LinkProps & { to: string }) {
  return <MuiLink {...props} component={RouterLink} />;
}
