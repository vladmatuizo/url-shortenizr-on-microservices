import React, {Component} from "react";
import LinkStatistic from "./LinkStatistic";

class LinkStatisticTable extends Component {

    render() {
        if (this.props.linkStatistics == null) {
            return <div/>;
        }
        const linkStatistics = this.props.linkStatistics.map((linkStatistic, index) =>
            <LinkStatistic key={index} linkStatistic={linkStatistic}/>
        );
        return (
            <div>
                {linkStatistics}
            </div>
        )
    }
}
export default LinkStatisticTable;