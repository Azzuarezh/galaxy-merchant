import React,{ useState, useEffect} from 'react';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TextField from '@material-ui/core/TextField';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';

import Title from './Title';


function preventDefault(event) {
  event.preventDefault();
}

const useStyles = makeStyles((theme) => ({
  seeMore: {
    marginTop: theme.spacing(3),
  },
}));

export default function CheckPrice (props) {

    const [result, setResult] = useState({});
    const [show,setShown]     = useState(false);
    const [input,setInput]    = useState('');
    const [type,setType]      = useState('');

    const handleChange = (event) => {
        setInput(event.target.value);
    }

    const handleQuery = () =>{
      console.log('clicked!')
      // Simple POST request with a JSON body using fetch
      
      fetch('/api/calculate',  {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: input
      })
          .then(response => response.json())
          .then(data => {
            console.log('data:', data)
            if(data.status == 0){
              setResult(data.data);
              setShown(true);
            }
            else{
              alert(data.data.error)
              setResult({});
              setShown(false);
            }
          }).catch(error =>{
            alert("sorry, could not connect to server.")
            console.log(error)
          });
    }

  return (
    <React.Fragment>
      <Title>Check your Items value</Title>
      <p>You can enquiry the value of intergalactic numeral to Roman/Numeric and also calculate the total price of materials. <br/>
      Please follow these example below:</p>
      <ul>
        <li>use <code>how much is</code> followed by intergalactic symbols to convert to Roman/Numeric value</li>
        <li>use <code>how many credits is</code> followed by intergalactic symbols and material name </li>
        <li>end the sentence with space and quotation mark <code>?</code></li>
        <li>example using how much is : <code>"how much is pish tegj glob glob ?"</code> without quotes </li>
        <li>example using how many credits is : <code>"how many credits is pish tegj glob glob silver ?"</code> without quotes </li>
      </ul>

      <TextField id="filled-basic" label="Input your query here ..." variant="filled" value={input} size="large" onChange={handleChange}/>
      <Button variant="contained" color="primary" size="small" onClick={handleQuery}>
        Go
      </Button>
      <br/>
       <br/>
      {(show) ? 
      (result.type =='calculate') ?
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Material</TableCell>
            <TableCell>Roman Value</TableCell>
            <TableCell>Qty</TableCell>
            <TableCell>Price</TableCell>
            <TableCell>Total</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <TableRow>
            <TableCell>{result.material}</TableCell>
            <TableCell>{result.romanValue}</TableCell>
            <TableCell>{result.numericValue}</TableCell>
            <TableCell>{result.price} Credits</TableCell>
            <TableCell>{result.value} Credits</TableCell>
            </TableRow>
        </TableBody>
      </Table>: 
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Roman Value</TableCell>
            <TableCell>Numerical value</TableCell>
            <TableCell>Explanation</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <TableRow>
            <TableCell>{result.romanValue}</TableCell>
            <TableCell>{result.numericValue}</TableCell>
            <TableCell>{result.explanation}</TableCell>
            </TableRow>
        </TableBody>
      </Table>
      :""
      }
     

    </React.Fragment>
  );
}