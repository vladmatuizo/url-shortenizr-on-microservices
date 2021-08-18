import React, {Component} from "react";

class ShortenizrControl extends Component {

    constructor(props) {
        super(props);
        this.state = {
            sourceLink : "",
            createdLink : null
        }
        this.handleShortenLinkClick = this.handleShortenLinkClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleShortenLinkClick() {
        console.log('Creating link from: ' + this.state.sourceLink);
        const response = await fetch(`http://localhost:8000/createUrl`, {
            method: 'PUT',
            body: this.state.sourceLink,
            headers: {
                'Content-Type': 'text/plain',
            }
        });
        let link = await response.text();
        this.setState({
            linkKey: "",
            createdLink: link
        })
    }

    handleChange(event) {
        this.setState({sourceLink: event.target.value});
    }


    render() {
        return (
            <div>
                <input type="text" value={this.state.sourceLink} onChange={this.handleChange} />
                <button onClick={this.handleShortenLinkClick}>Create tiny link</button>
                <p>
                    <a href={this.state.createdLink}>{this.state.createdLink}</a>
                </p>
            </div>
        );
    }
}
export default ShortenizrControl;