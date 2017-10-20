import React, {Component} from 'react';
import Img from 'react-image';

class List extends Component {

    state = {
        location: 6,
    }

    renderImage(row) {
        if(row.hasOwnProperty('photos')) {
            var apiKey = 'AIzaSyCopUMtPGt0pBDV7N4DVI0yqDeVECRxFBw';
            var src = 'https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=' + row.photos[0].photo_reference + '&key=' + apiKey;
            return <Img className="image__container" src={[src]}/>
        }
    }


    render() {

        return (
            <div className="card__container">
                {this.props.locations.length > 0 ?
                    <div>
                        <h3>{this.props.locations[this.state.location].name}</h3>
                        <p>{this.props.locations[this.state.location].extract}</p>
                        {this.renderImage(this.props.locations[this.state.location])}
                    </div> :
                    <div></div>}
            </div>
        );

    }
}


export default List;

/* return (
            this.props.locations.map((row, index) => {
                    return <div className="card__container" key={index}>
                        <h3>{row.name}</h3>
                        <p>{row.extract}</p>
                        {this.renderImage(row)}
                    </div>
                }
            )
        );*/