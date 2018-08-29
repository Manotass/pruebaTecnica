import React, { Component } from "react";
import Responsable from "./responsable";
import ReactDOM from "react-dom";

class AsignarRecurso extends Component {
  constructor() {
    super();
    this.state = {
      products: [],
      prueba: "prueba"
    };
  }

  componentDidMount() {
    fetch("http://localhost:8090/recursosDisponibles")
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  asignarRecurso(id) {
    this.setState({ prueba: "CAMBIO " + window.state.test });

    fetch(
      "http://localhost:8090/asignarRecurso?recursoId=" +
        id +
        "&responsableId=" +
        window.state.test
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  cargarListaRecursos(i, index) {
    return (
      <tr key={i.id}>
        <td>{i.serial}</td>
        <td>{i.marca}</td>
        <td>{i.tipo}</td>
        <td>{i.proveedor}</td>
        <td>{i.valorCOmercial}</td>
        <td>{i.fechaDeCompra}</td>
        <td>
          <button
            className="btn btn-primary"
            onClick={this.asignarRecurso.bind(this, i.id)}
          >
            Asignar
          </button>
        </td>
      </tr>
    );
  }
  cargarResponsables() {
    ReactDOM.render(
      React.createElement(Responsable),
      document.getElementById("root")
    );
  }

  render() {
    return (
      <div className="table-responsive" style={{ textAlign: "center" }}>
        <h3>RECURSOS DISPONIBLES</h3>
        <table
          className="table table-striped table-bordered"
          style={{ width: "70%", marginLeft: "15%", marginRight: "20%" }}
        >
          <thead className="thead-dark">
            <tr>
              <th scope="col">serial</th>
              <th scope="col">marca</th>
              <th scope="col">tipo</th>
              <th scope="col">proveedor</th>
              <th scope="col">Valor comercial</th>
              <th scope="col">fecha de compra</th>
              <th scope="col">opciones</th>
            </tr>
          </thead>
          <tbody>
            {this.state.products.map(this.cargarListaRecursos, this)};
          </tbody>
        </table>
        <button
          className="btn btn-primary"
          onClick={this.cargarResponsables.bind(this)}
        >
          Volver
        </button>
      </div>
    );
  }
}

export default AsignarRecurso;
