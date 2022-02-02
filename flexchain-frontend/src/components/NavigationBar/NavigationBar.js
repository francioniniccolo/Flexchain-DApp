import React from "react";
import Navbar from 'react-bootstrap/Navbar'
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";

const NavigationBar = ()=>{

   return(
    <Navbar bg="primary" variant="dark">
        <Container>
            <Navbar.Brand href="#home">Flexchain</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link href="/modeler">Choreography Modeler</Nav.Link>
                {/*<Nav.Link href="/deploy">Deploy Contract</Nav.Link>*/}
                <Nav.Link href="/update">Update Model</Nav.Link>
                <Nav.Link href="/execute">Execute</Nav.Link>
            </Nav>
        </Container>
    </Navbar>
   );
};

export default NavigationBar;