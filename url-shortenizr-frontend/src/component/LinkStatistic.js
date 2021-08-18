import React, {Component} from "react";

class LinkStatistic extends Component {
    render() {
        let link = `http://localhost:8000/${this.props.linkStatistic.shortenizrKey}`;
        return (
            <div className="LinkStatistic">
                <table border="1px" width="100%">
                    <tbody>
                        <tr>
                            <td width="10%">Shortened link</td>
                            <td>
                                <a href={link}>{link}</a>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Count of link followings</td>
                            <td>{this.props.linkStatistic.linkFollowingCount}</td>
                        </tr>
                        <tr>
                            <td width="10%">Date of link creation</td>
                            <td>{this.props.linkStatistic.creationDate}</td>
                        </tr>
                        <tr>
                            <td width="10%">Last following date</td>
                            <td>{this.props.linkStatistic.lastFollowingDate}</td>
                        </tr>
                        <tr>
                            <td width="10%">Source link</td>
                            <td>{this.props.linkStatistic.sourceLink}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

export default LinkStatistic;