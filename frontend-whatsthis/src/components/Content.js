import React, {Component} from 'react';
import {geolocated} from 'react-geolocated';
import 'whatwg-fetch'
import List from './List'

class Content extends Component {

    state = {
        locations: []
    }

    getInfo = () => {

        var url = 'http://localhost:8080/locations?lat=59.5214305&lng=17.9011611';//this.props.coords.latitude+'&lng='+this.props.coords.longitude;

        fetch(url)
            .then(function(response) {

                return response.json()
            }).then((response) => {
               this.setState({
                  locations: response
               });
        });

    }



    render() {
        return (
            <div>

                {(this.props.isGeolocationAvailable && this.props.isGeolocationEnabled ?
                        <button type="submit" onClick={this.getInfo}>Find</button> :
                        <div>Geolocation is not enabled</div>
                )}


                <List locations={this.state.locations}/>
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