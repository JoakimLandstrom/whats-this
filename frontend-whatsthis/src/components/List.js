import React, {Component} from 'react';

class List extends Component {


    render() {

        return (
            this.props.locations.map((row, index) => {
                    return <div className="card__container" key={index}>
                        <h3>{row.name}</h3>
                        <p>{row.extract}</p>

                    </div>
                }
            )
        );
    }
}


export default List;