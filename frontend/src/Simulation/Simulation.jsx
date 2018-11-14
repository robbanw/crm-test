import React, {Component} from "react";
import axios from 'axios';

import './Simulation.css';

const maxSimulations = 10000000;

class Simulation extends Component {

    constructor(props) {
        super(props);
        this.state = {
            switchDoor: "false"
        };
    }

    render() {
        return (
            <div className="Simulation">
                <form className="simulation-form">
                    <h2>Simulation input</h2>
                    { this.state.error && <p className="simulation-error">{this.state.error}</p> }
                    <div className="form-group">
                        <label className="simulation-label" htmlFor="numSim">Select number of simulations to run:</label>
                        <input id="numSim" className="form-control" type="number" min="1" max="10000000" name="numSimulations" placeholder="Type number of simulations..." onChange={e => this.onChange(e)}/>
                    </div>
                    <div className="form-group">
                        <label className="simulation-label">Switch door:</label>
                        <label htmlFor="switchYes" className="simulation-radio-label">
                            <input id="switchYes" className="simulation-radio" type="radio" name="switchDoor" value="true" checked={this.state.switchDoor === "true"} onChange={e => this.onChange(e)}/>
                        Yes</label>
                        <label htmlFor="switchNo" className="simulation-radio-label">
                            <input id="switchNo" className="simulation-radio" type="radio" name="switchDoor" value="false" checked={this.state.switchDoor === "false"} onChange={e => this.onChange(e)}/>
                        No</label>
                    </div>
                    <button className="btn btn-secondary" onClick={(e) => this.onSubmit(e)}>Submit</button>
                </form>
                { this.renderSimulationResult() }
            </div>
        );
    }

    renderSimulationResult() {
        if (!this.state.output || this.state.error) {
            return <div className="simulation-result hidden"></div>;
        }

        return (
            <div className="simulation-result">
                <h2>Simulation result</h2>
                <p>
                    Number of cars: <span className="simulation-car">{this.state.output.numCars}</span>.
                    Number of goats: <span className="simulation-goat">{this.state.output.numGoats}</span>.
                </p>
            </div>
        );
    }

    onChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    onSubmit = e => {
        e.preventDefault();
        if (!this.state.numSimulations || this.state.numSimulations < 1 || this.state.numSimulations > maxSimulations){
            this.setState({error: "Invalid input. Valid range for number of simulations is 1 - 10 000 000."});
            return;
        }

        this.getSimulationResult();
    };

    getSimulationResult() {
        axios.get('simulate?numSimulations=' + this.state.numSimulations + '&switchDoor=' + this.state.switchDoor)
            .then(res => {
                this.setState({
                    output: {
                        numGoats: res.data.numGoats,
                        numCars: res.data.numCars
                    },
                    error: null
                });
            })
            .catch(error => {
                console.log(error.response);
                if (error.response.status === 400) {
                    this.setState({error: "Invalid input. Valid range for number of simulations is 1 - 10 000 000."});
                } else {
                    this.setState({error: "Something went wrong... Please try again!"});
                }
            });
    }

}

export default Simulation;