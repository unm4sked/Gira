import React from 'react'
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
    <div className="container">
        <a className="navbar-brand" href="/">
        <i className="fa fa-calendar-check"> Gira </i> 
        </a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
            <span className="navbar-toggler-icon" />
        </button>
        <Link  to="/raport">
            <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Generate raport</button>
        </Link>
    </div>
</nav>
  )
}
