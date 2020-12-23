import React,{ useState, useEffect} from 'react';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Title from './Title';

//import Gold from './resources/images/gold.png';
//import Silver from './resources/images/silver.png';
//import Iron from './resources/images/iron.png';

// Generate Order Data
function createData(id, IntergalacticUnit , RomanSymbol, Numeric) {
  return { id, IntergalacticUnit , RomanSymbol, Numeric };
}

const rows = [
  createData(0, 'glob', 'I', '1'),
  createData(1, 'prok', 'V', '5'),
  createData(2, 'pish', 'X', '10'),
  createData(3, 'tegj', 'L', '50'),
  createData(3, 'sjoice', 'C', '100'),
  createData(3, 'crexs', 'D', '500'),
  createData(3, 'bjork', 'ML', '1000'),
];

function preventDefault(event) {
  event.preventDefault();
}

const useStyles = makeStyles((theme) => ({
  seeMore: {
    marginTop: theme.spacing(3),
  },
}));


export default function UnitConversion (props) {
  const classes = useStyles();
  return (
    <React.Fragment>
      <Title>Unit Conversion</Title>
      <Table size="small">
        <TableHead >
          <TableRow>
            <TableCell>InterGalactic symbol</TableCell>
            <TableCell>Roman symbol</TableCell>
            <TableCell>Numerical value</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.id}>
              <TableCell>{row.IntergalacticUnit }</TableCell>
              <TableCell>{row.RomanSymbol}</TableCell>
              <TableCell>{row.Numeric}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

    </React.Fragment>
  );
}