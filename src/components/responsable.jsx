import React, { Component } from "react";
import ReactDOM from "react-dom";
import RecursoPorResponsable from "./recursosPorResponsable";
import AsignarRecurso from "./asignarRecursos";
import Recurso from "./recurso";

class Responsable extends Component {
  constructor() {
    super();
    this.state = {
      responsables: [],
      tiposResponsables: [],
      responsableSeleccionado: "0"
    };
  }

  componentDidMount() {
    fetch("http://localhost:8090/responsablesTotales")
      .then(response => response.json())
      .then(response => {
        this.setState({ responsables: response });
      });

    fetch("http://localhost:8090/tiposResponsableTotales")
      .then(response => response.json())
      .then(response => {
        this.setState({ tiposResponsables: response });
      });
  }
  eliminarResponsable(id) {
    fetch("http://localhost:8090/eliminarResponsable?id=" + id)
      .then(response => response.json())
      .then(response => {
        this.setState({ responsables: response });
      });
  }

  cargarListaResponsables(i, index) {
    return (
      <tr>
        <td>{i.nombre}</td>
        <td>{i.telefono}</td>
        <td>{i.tipo}</td>
        <td>
          <button
            className="btn btn-danger"
            onClick={this.eliminarResponsable.bind(this, i.id)}
          >
            eliminar
          </button>
          &nbsp;
          <button
            className="btn btn-primary"
            onClick={this.cargarRecursosResponsable.bind(this, i.id)}
          >
            Recursos Asignados
          </button>
          &nbsp;
          <button
            className="btn btn-primary"
            onClick={this.asignarRecursosResponsable.bind(this, i.id)}
          >
            Asignar Recurso
          </button>
        </td>
      </tr>
    );
  }

  cargarListaTiposResponsable(i, index) {
    return (
      <div className="custom-control custom-radio">
        <input
          type="radio"
          className="custom-control-input"
          id={i.id}
          onClick={this.tiposResponsablesSelecionado.bind(this, i.id)}
          name="groupOfDefaultRadios"
        />
        <label className="custom-control-label" for={i.id}>
          {i.descripcion}
        </label>
      </div>
    );
  }
  tiposResponsablesSelecionado(id) {
    {
      this.setState({ responsableSeleccionado: id });
    }
  }
  agregarResponsable() {
    fetch(
      "http://localhost:8090/agregarResponsable?nombre=" +
        this.refs.nombreAgregar.value +
        "&telefono=" +
        this.refs.telefonoAgregar.value +
        "&tipo=" +
        this.state.responsableSeleccionado
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ responsables: response });
      });
  }

  cargarRecursosResponsable(id) {
    window.state = { test: id };
    ReactDOM.render(
      React.createElement(RecursoPorResponsable),
      document.getElementById("root")
    );
  }
  asignarRecursosResponsable(id) {
    window.state = { test: id };
    ReactDOM.render(
      React.createElement(AsignarRecurso),
      document.getElementById("root")
    );
  }
  buscarPorNombre() {
    fetch(
      "http://localhost:8090/buscarResponsablePorNombre?nombre=" +
        this.refs.nombreBuscar.value
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ responsables: response });
      });
  }
  cargarRecursos() {
    ReactDOM.render(
      React.createElement(Recurso),
      document.getElementById("root")
    );
  }

  render() {
    return (
      <div className="table-responsive" style={{ textAlign: "center" }}>
        <br />
        <h1>RESPONSABLES</h1>
        <br />
        <table
          className="table table-striped table-bordered"
          style={{ width: "70%", marginLeft: "15%", marginRight: "20%" }}
        >
          <thead className="thead-dark">
            <tr>
              <th scope="col">
                nombre
                <input
                  type="text"
                  ref="nombreBuscar"
                  onKeyUp={this.buscarPorNombre.bind(this)}
                  placeholder="filtrar..."
                />
              </th>
              <th scope="col">telefono</th>
              <th scope="col">tipo responsable</th>
              <th scope="col" style={{ width: "450px" }}>
                opciones
              </th>
            </tr>
          </thead>
          <tbody>
            {this.state.responsables.map(this.cargarListaResponsables, this)}
          </tbody>
        </table>
        <h3>AGREGAR RESPONSABLE</h3>
        <table
          className="table table-striped table-bordered"
          style={{ width: "70%", marginLeft: "15%", marginRight: "20%" }}
        >
          <thead className="thead-dark" />
          <tr>
            <th>
              nombre
              <input type="text" ref="nombreAgregar" required />
            </th>
            <th>
              telefono
              <input type="text" ref="telefonoAgregar" required />
            </th>
            <th style={{ textAlign: "left" }}>
              Tipo
              {this.state.tiposResponsables.map(
                this.cargarListaTiposResponsable,
                this
              )}
            </th>
            <th>
              <button
                className="btn btn-primary"
                onClick={this.agregarResponsable.bind(this)}
              >
                Agregar
              </button>
            </th>
          </tr>
        </table>
        <button
          className="btn btn-info"
          onClick={this.cargarRecursos.bind(this)}
        >
          RECURSOS
        </button>
      </div>
    );
  }
}

export default Responsable;
