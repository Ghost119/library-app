import { useOktaAuth } from "@okta/okta-react";
import { Link, NavLink } from "react-router-dom";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import React from "react";

export const Navbar: React.FC = () => {
    const {oktaAuth, authState} = useOktaAuth();
    
    if(!authState){
        return (
            <SpinnerLoading/>
        );
    }
    
    const handleLogout = async () => oktaAuth.signOut();

    console.log(authState);

    return (
        <nav className="navbar navbar-expand-lg navbar-dark main-color">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/home">LOGO</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <NavLink className="nav-link" aria-current="page" to="/home">Home</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/search">Search Books</NavLink>
                        </li>
                    </ul>
                    <ul className='navbar-nav ms-auto'>
                        {
                            !authState.isAuthenticated ?
                            <li>
                                <Link className="btn btn-outline-light" to='/login'>Sign In</Link>        
                            </li>
                            :
                            <li>
                                <button className="btn btn-outline-light" onClick={handleLogout}>Logout</button> 
                            </li>
                        }
                    </ul>
                </div>
            </div>
        </nav>
    );
}