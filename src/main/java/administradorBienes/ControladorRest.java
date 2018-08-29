package administradorBienes;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ControladorRest {
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recursosTotales")
    public List<Recurso> recursosTotales(){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.recursoList();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recursoPorSerial")
    public List<Recurso> recursoPorSerial(@RequestParam(value="serial") String serial){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.buscarRecursoPorSerial(serial);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recursosPorMarca")
    public List<Recurso> recursosPorMarca(@RequestParam(value="marca") String marca){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.buscarRecursoPorMarca(marca);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recursosPorTipo")
    public List<Recurso> recursosPorTipo(@RequestParam(value="tipo") String tipo){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.buscarRecursoPorTipo(tipo);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/eliminarRecurso")
    public List<Recurso> eliminarRecurso(@RequestParam(value="id") long id){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();

        return conectorBd.eliminarRecurso(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/agregarRecurso")
    public List<Recurso> agregarRecurso(@RequestParam(value="serial") String serial,@RequestParam(value="marca") String marca,@RequestParam(value="tipo") String tipo,@RequestParam(value="proveedor") String proveedor,@RequestParam(value="valorComercial") String valorComercial,@RequestParam(value="fechaCompra") String fechaCompra,@RequestParam(value="estado") String estado){
       Recurso recurso= new Recurso(0,serial,marca,tipo,proveedor,Long.parseLong(valorComercial),fechaCompra,estado,"0");
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return conectorBd.agregarRecurso(recurso);

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/responsablesTotales")
    public List<Responsable> responsablesTotales(){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return conectorBd.responsableList();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/agregarResponsable")
    public List<Responsable> agregarResponsable(@RequestParam(value="nombre") String nombre,@RequestParam(value="telefono") String telefono,@RequestParam(value="tipo") String tipo){
        Responsable responsable=new Responsable(0,nombre,telefono,tipo);
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return conectorBd.agregarResponsable(responsable);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/eliminarResponsable")
    public List<Responsable> eliminarResponsable(@RequestParam(value="id") long id){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return conectorBd.eliminarResponsable(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recursosPorResponsable")
    public List<Recurso> recursosPorResponsable(@RequestParam(value="id") long id){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.buscarRecursosPorResponsable(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/buscarResponsablePorId")
    public List<Responsable> buscarResponsable(@RequestParam(value="id") long id){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.buscarResponsablePorId(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/eliminarRecursoResponsable")
    public List<Recurso> eliminarRecursoResponsable(@RequestParam(value="recursoId") long recursoId,@RequestParam(value="responsableId") long responsableId){
        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.eliminarRecursoResponsable(recursoId,responsableId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recursosDisponibles")
    public List<Recurso> recursosDisponibles(){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.recursosDisponibles();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/asignarRecurso")
    public List<Recurso> AsignarRecurso(@RequestParam(value="recursoId") long recursoId,@RequestParam(value="responsableId") long responsableId){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.asignarRecurso(recursoId,responsableId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/tiposResponsableTotales")
    public List<TipoResponsable> tiposResponsableTotales(){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.tiposResponsableTotales();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/tiposProductoTotal")
    public List<TipoProducto> tiposProductoTotal(){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.tiposProductoTotal();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/estadosRecursosTotales")
    public List<EstadoRecurso> estadosRecursosTotales(){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.estadosRecursosTotales();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/buscarResponsablePorNombre")
    public List<Responsable> buscarResponsablePorNombre(@RequestParam(value="nombre") String nombre){

        ConectorBd conectorBd =new ConectorBd();
        conectorBd.start();
        return  conectorBd.buscarResponsablePorNombre(nombre);
    }
}
