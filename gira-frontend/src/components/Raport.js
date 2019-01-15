import React, { Component } from 'react';
import '../styles.css';
import { Link } from 'react-router-dom';
import {Progress, Table} from 'reactstrap'

class Raport extends Component {
    constructor(){
        super();
        this.state= {
            data: []
        };
        this.loadData = this.loadData.bind(this);
    }
    loadData(){
        fetch('http://localhost:8080/api/board/raport')
        .then(result => {
            return result.json();
        })
        .then(d => {
            console.log(d)
            this.setState({data: [
                d.numberOfTasks,
                d.doneTaks,
                d.inProgressTasks,
                d.toDoTasks
            ]})
        })
    }



    componentDidMount(){
        this.loadData();
    }

    render(){
        console.log(this.state)
        return (
        <div>
            <Link to="/" className="btn btn-light buttonReturnRaport">
                    <i className="fa fa-clipboard-list"> Back to Board</i>
                    </Link>
            <div className="border rounded raportTasks">
                <div className="text-center">
                    <h1>Raport</h1>
                </div>
                <div className="">
                
                    <h3 className="border border-light rounded oneitem">Total tasks: {this.state.data[0]}</h3> 
                    <h3 className="border border-light rounded oneitem">Done tasks: {this.state.data[1]}</h3>
                    <Progress animated color="info" value={this.state.data[1]*100/this.state.data[0]} >{Math.round(this.state.data[1]*100/this.state.data[0])}%</Progress>
                    <h3 className="border border-light rounded oneitem">In progress tasks: {this.state.data[2]}</h3>
                    <Progress animated color="info" value={this.state.data[2]*100/this.state.data[0]} >{Math.round(this.state.data[2]*100/this.state.data[0])}%</Progress>
                    <h3 className="border border-light rounded oneitem">To do tasks: {this.state.data[3]}</h3>
                    <Progress animated color="info" value={this.state.data[3]*100/this.state.data[0]} >{Math.round(this.state.data[3]*100/this.state.data[0])}%</Progress>
                    {/* <Progress animated color="success" value={this.state.data[1]*100/this.state.data[0]} >{this.state.data[1]*100/this.state.data[0]}%</Progress> */}
                    
                    <h4 className="border border-light rounded oneitem text-center">How much work remains until the end</h4>
                    <Progress animated color="danger" value={this.state.data[1]*100/this.state.data[0]} >{Math.round(this.state.data[1]*100/this.state.data[0])}%</Progress>


                </div>
            </div>
        </div>
        );
    }
}

export default Raport;