import React, { Component } from "react";
import Responsable from "./responsable";
import ReactDOM from "react-dom";

class Recurso extends Component {
  constructor() {
    super();
    this.state = {
      products: [],
      recursoSeleccionado: "0",
      EstadoSeleccionado: "0",
      tiposRecursos: [],
      estadoRecurso: []
    };
  }

  componentDidMount() {
    fetch("http://localhost:8090/recursosTotales")
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });

    fetch("http://localhost:8090/tiposProductoTotal")
      .then(response => response.json())
      .then(response => {
        this.setState({ tiposRecursos: response });
      });
    fetch("http://localhost:8090/estadosRecursosTotales")
      .then(response => response.json())
      .then(response => {
        this.setState({ estadoRecurso: response });
      });
  }
  buscarPorSerial() {
    fetch(
      "http://localhost:8090/recursoPorSerial?serial=" + this.refs.serial.value
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  buscarPorMarca() {
    fetch(
      "http://localhost:8090/recursosPorMarca?marca=" + this.refs.marca.value
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  buscarPorTipo() {
    fetch("http://localhost:8090/recursosPorTipo?tipo=" + this.refs.tipo.value)
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  eliminarRecurso(id) {
    fetch("http://localhost:8090/eliminarRecurso?id=" + id)
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }

  cargarListaRecursos(i, index) {
    return (
      <tr key={i.id}>
        <td>{i.id}</td>
        <td>{i.serial}</td>
        <td>{i.marca}</td>
        <td>{i.tipo}</td>
        <td>{i.proveedor}</td>
        <td>{i.valorCOmercial}</td>
        <td>{i.fechaDeCompra}</td>
        <td>{i.estado}</td>
        <td>{i.estadoAsignacion}</td>
        <td>
          <button
            className="btn btn-danger"
            onClick={this.eliminarRecurso.bind(this, i.id)}
          >
            eliminar
          </button>
        </td>
      </tr>
    );
  }

  agregarRecurso() {
    fetch(
      "http://localhost:8090/agregarRecurso?serial=" +
        this.refs.serialAgregar.value +
        "&marca=" +
        this.refs.marcaAgregar.value +
        "&tipo=" +
        this.state.recursoSeleccionado +
        "&proveedor=" +
        this.refs.proveedorAgregar.value +
        "&valorComercial=" +
        this.refs.ValorComercialAgregar.value +
        "&fechaCompra=" +
        this.refs.fechaCompraAgregar.value +
        "&estado=" +
        this.state.EstadoSeleccionado
    )
      .then(response => response.json())
      .then(response => {
        this.setState({ products: response });
      });
  }
  cargarListaTiposRecursos(i, index) {
    return (
      <div className="custom-control custom-radio">
        <input
          type="radio"
          className="custom-control-input"
          id={"Recurso" + i.id}
          onClick={this.tiposRecursoSelecionado.bind(this, i.id)}
          name="groupOfDefaultRadios"
        />
        <label className="custom-control-label" for={"Recurso" + i.id}>
          {i.descripcion}
        </label>
      </div>
    );
  }
  cargarListaEstadoRecursos(i, index) {
    return (
      <div className="custom-control custom-radio">
        <input
          type="radio"
          className="custom-control-input"
          id={"Estado" + i.id}
          onClick={this.estadoRecursoSelecionado.bind(this, i.id)}
          name="groupOfDefaultRadios2"
        />
        <label className="custom-control-label" for={"Estado" + i.id}>
          {i.descripcion}
        </label>
      </div>
    );
  }
  tiposRecursoSelecionado(id) {
    {
      this.setState({ recursoSeleccionado: id });
    }
  }
  estadoRecursoSelecionado(id) {
    {
      this.setState({ EstadoSeleccionado: id });
    }
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
        <h1>RECURSOS</h1>

        <br />
        <table
          className="table table-striped table-bordered"
          style={{ width: "70%", marginLeft: "15%", marginRight: "20%" }}
        >
          <thead className="thead-dark">
            <tr>
              <th scope="col">id</th>
              <th scope="col">
                serial
                <input
                  type="text"
                  ref="serial"
                  onKeyUp={this.buscarPorSerial.bind(this)}
                  placeholder="filtrar..."
                />
              </th>
              <th scope="col">
                marca
                <input
                  type="text"
                  ref="marca"
                  onKeyUp={this.buscarPorMarca.bind(this)}
                  placeholder="filtrar..."
                />
              </th>
              <th scope="col">
                tipo
                <input
                  type="text"
                  ref="tipo"
                  onKeyUp={this.buscarPorTipo.bind(this)}
                  placeholder="filtrar..."
                />
              </th>
              <th scope="col">proveedor</th>
              <th scope="col">Valor comercial</th>
              <th scope="col">fecha de compra</th>
              <th scope="col">estado</th>
              <th scope="col">estado de asginacion</th>
              <th scope="col">opciones</th>
            </tr>
          </thead>
          <tbody>
            {this.state.products.map(this.cargarListaRecursos, this)};
          </tbody>
        </table>
        <h3 style={{ textAlign: "center" }}>AGREGAR RECURSO</h3>
        <table
          className="table table-striped table-bordered"
          style={{ width: "70%", marginLeft: "15%", marginRight: "20%" }}
        >
          <thead className="thead-dark" />
          <tr>
            <th>
              <form id="notEmptyForm" class="form-horizontal">
                serial
                <input
                  type="text"
                  className="form-control"
                  ref="serialAgregar"
                  required
                />
              </form>
            </th>
            <th>
              marca
              <input type="text" ref="marcaAgregar" required />
            </th>
            <th style={{ textAlign: "left" }}>
              Tipo
              {this.state.tiposRecursos.map(
                this.cargarListaTiposRecursos,
                this
              )}
            </th>
            <th>
              proveedor
              <input type="text" ref="proveedorAgregar" required />
            </th>
            <th>
              Valor comercial
              <input type="text" ref="ValorComercialAgregar" required />
            </th>
            <th>
              fecha de compra
              <input type="date" ref="fechaCompraAgregar" required />
            </th>
            <th style={{ textAlign: "left" }}>
              Estado
              {this.state.estadoRecurso.map(
                this.cargarListaEstadoRecursos,
                this
              )}
            </th>
            <th>
              <button
                className="btn btn-primary"
                onClick={this.agregarRecurso.bind(this)}
              >
                Agregar
              </button>
            </th>
          </tr>
        </table>
        <button
          className="btn btn-info"
          onClick={this.cargarResponsables.bind(this)}
        >
          RESPONSABLES
        </button>
      </div>
    );
  }
  /*
  formatRecurso() {
    const { count } = this.state;
    return count === 0 ? "zero" : count;
  }*/
}

export default Recurso;
