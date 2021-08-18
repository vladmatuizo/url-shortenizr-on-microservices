import React, {Component} from "react";
import LinkStatistic from "./LinkStatistic";
import LinkStatisticTable from "./LinkStatisticTable";

class LinkStatisticControl extends Component {

    constructor(props) {
        super(props);
        this.state = {
            linkKey: "",
            linkStatistic: []
        }
        this.handleGetLinkStatisticClick = this.handleGetLinkStatisticClick.bind(this);
        this.handleGetAllStatisticClick = this.handleGetAllStatisticClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleGetLinkStatisticClick() {
        let response = await fetch(`http://localhost:8100/statistic/${this.state.linkKey}`);
        let json = await response.json();
        this.setState({
            linkKey: "",
            linkStatistic: json
        })
    }

    async handleGetAllStatisticClick() {
        let response = await fetch('http://localhost:8100/statistic');
        let json = await response.json();

        this.setState({
            linkKey: "",
            linkStatistic: json
        })
    }

    handleChange(event) {
        this.setState({linkKey: event.target.value});
    }

    createLinkStatistics(linkStatistics) {
        if (linkStatistics === undefined) {
            return <div/>;
        }
        if (!Array.isArray(linkStatistics)) {
            return <LinkStatistic linkStatistic={linkStatistics}/>;
        }
        return <LinkStatisticTable linkStatistics={linkStatistics}/>;
    }


    render() {
        let linkStatistics = this.createLinkStatistics(this.state.linkStatistic);
        return (
            <div>
                <input type="text" value={this.state.linkKey} onChange={this.handleChange}/>
                <button onClick={this.handleGetLinkStatisticClick}>Get link statistics</button>
                <button onClick={this.handleGetAllStatisticClick}>Get all links statistics</button>
                {linkStatistics}
            </div>
        );
    }
}

export default LinkStatisticControl;