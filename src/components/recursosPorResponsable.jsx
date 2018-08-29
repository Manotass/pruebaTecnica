import React, { Component } from "react";
import Responsable from "./responsable";
import ReactDOM from "react-dom";

class RecursoPorResponsable extends Component {
  constructor(test) {
    super();
    this.state = {
      products: [],
      responsables: [],
      responsableId: "0"
    };
  }

  componentDidMount() {
    fetch(
      "http://localhost:8090/recursosPorResponsable?id=" + window.state.test
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
    fetch(
      "http://localhost:8090/buscarResponsablePorId?id=" + window.state.test
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ responsables: response });
      });
  }
  cargarResponsable(i, index) {
    return (
      <tr>
        <td>{i.nombre}</td>
        <td>{i.telefono}</td>
        <td>{i.tipo}</td>
      </tr>
    );
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
            className="btn btn-danger"
            onClick={this.eliminarRecurso.bind(this, i.id)}
          >
            Desasignar
          </button>
        </td>
      </tr>
    );
  }
  eliminarRecurso(id) {
    fetch(
      "http://localhost:8090/eliminarRecursoResponsable?recursoId=" +
        id +
        "&responsableId=" +
        window.state.test
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  agregarRecurso() {
    fetch(
      "http://localhost:8090/agregarRecurso?serial=" +
        this.refs.serialAgregar.value +
        "&marca=" +
        this.refs.marcaAgregar.value +
        "&tipo=" +
        this.refs.tipoAgregar.value +
        "&proveedor=" +
        this.refs.proveedorAgregar.value +
        "&valorComercial=" +
        this.refs.ValorComercialAgregar.value +
        "&fechaCompra=" +
        this.refs.fechaCompraAgregar.value
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
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
        <h3>RESPONSABLE</h3>
        <br />
        <table
          className="table table-striped table-bordered"
          style={{ width: "70%", marginLeft: "15%", marginRight: "20%" }}
        >
          <thead className="thead-dark">
            <tr>
              <th scope="col">nombre</th>
              <th scope="col">telefono</th>
              <th scope="col">tipo responsable</th>
            </tr>
          </thead>
          <tbody>
            {this.state.responsables.map(this.cargarResponsable, this)}
          </tbody>
        </table>
        <h3>RECURSOS ASIGNADOS</h3>
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
            {this.state.products.map(this.cargarListaRecursos, this)}
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
export default RecursoPorResponsable;
