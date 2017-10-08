import React, {Component} from 'react';
import {geolocated} from 'react-geolocated';
import 'whatwg-fetch'

class Content extends Component {

    state = {

    }

    getInfo = () => {

        var url = 'http://localhost:8080/locations?lat='+this.props.coords.latitude+'&lng='+this.props.coords.longitude;

        fetch(url)
            .then(function(response) {

                return response.json()
            }).then((response) => {
                console.log(response)
        });

    }



    render() {
        return (
            <div>

                {(this.props.isGeolocationAvailable && this.props.isGeolocationEnabled ?
                        <button type="submit" onClick={this.getInfo}>Find</button> :
                        <div>Geolocation is not enabled</div>
                )}

            </div>
        );
    }
}

export default geolocated({
    positionOptions: {
        enableHighAccuracy: true,
    },
    userDecisionTimeout: 5000,
})(Content);