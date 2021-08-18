import React, {Component} from "react";
import LinkStatisticControl from "./component/LinkStatisticControl";
import ShortenizrControl from "./component/ShortenizrControl";

class App extends Component {

    render() {
        return (
            <div className="App">
                <h2>Shortenizr Service</h2>
                <h3>A simple service that provide you tiny links from your large ones.</h3>
                <ShortenizrControl/>
                <br/>
                <h2>Link Statistics Service</h2>
                <h3>A simple service that provide statistics of created tiny links.</h3>
                <LinkStatisticControl/>
            </div>
        );
    }
}

export default App;
