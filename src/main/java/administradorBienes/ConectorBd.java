package administradorBienes;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConectorBd extends Thread implements Serializable  {

    private Connection connection=null;
    private ResultSet resultSet=null;
    private PreparedStatement preparedStatement=null;

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:" +System.getProperty("user.dir")+"\\administradorBienes.db");
            return connection;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void run(){        
    }

    public void desconectar(){

        try {
            connection.close();
            resultSet.close();

        }catch (Exception e){

        }
    }

    public List<Recurso> recursoList(){

        try {
            List<Recurso> recursoList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select recu.id,recu.serial,recu.marca,tire.descripcion as tipo_id,recu.proveedor,recu.valor_comercial,recu.fecha_compra,esre.descripcion as estado_id, (case when recu.estado_asignacion = 0 then 'Disponible' when recu.estado_asignacion = 1 then 'Asignado' end) as estado_asignacion from recurso recu inner join tipo_recurso tire on tire.id = recu.tipo_id inner join estado_recurso esre on esre.id=estado_id");
            resultSet=preparedStatement.executeQuery();
            Recurso recurso;
            while(resultSet.next()){
                recurso = new Recurso(
                        resultSet.getLong("id"),
                        resultSet.getString("serial"),
                        resultSet.getString("marca"),
                        resultSet.getString("tipo_id"),
                        resultSet.getString("proveedor"),
                        resultSet.getInt("valor_comercial"),
                        resultSet.getString("fecha_compra"),
                        resultSet.getString("estado_id"),
                        resultSet.getString("estado_asignacion")
                );
                recursoList.add(recurso);
            }
            return recursoList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }

    public List<Responsable> responsableList(){

        try {
            List<Responsable> recursoList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select repo.id,repo.nombre,repo.telefono,tire.descripcion from responsable repo inner join tipo_responsable tire on tire.id=repo.tipo_responsable");
            resultSet=preparedStatement.executeQuery();
            Responsable responsable;
            while(resultSet.next()){
                responsable = new Responsable(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                recursoList.add(responsable);
            }
            return recursoList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }

    public List<TipoResponsable> tiposResponsableTotales(){
        try {
            List<TipoResponsable> tipoResponsableList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select * from tipo_responsable");
            resultSet=preparedStatement.executeQuery();
            TipoResponsable tipoResponsable;
            while(resultSet.next()){
                tipoResponsable = new TipoResponsable(
                        resultSet.getString("id"),
                        resultSet.getString("descripcion")
                );
                tipoResponsableList.add(tipoResponsable);
            }
            return tipoResponsableList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }
    public List<TipoProducto> tiposProductoTotal(){
        try {
            List<TipoProducto> tipoResponsableList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select * from tipo_recurso");
            resultSet=preparedStatement.executeQuery();
            TipoProducto tipoProducto;
            while(resultSet.next()){
                tipoProducto = new TipoProducto(
                        resultSet.getLong("id"),
                        resultSet.getString("descripcion")
                );
                tipoResponsableList.add(tipoProducto);
            }
            return tipoResponsableList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }
    public List<EstadoRecurso> estadosRecursosTotales(){
        try {
            List<EstadoRecurso> estadoRecursos=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select * from estado_recurso");
            resultSet=preparedStatement.executeQuery();
            EstadoRecurso estadoRecurso;
            while(resultSet.next()){
                estadoRecurso = new EstadoRecurso(
                        resultSet.getString("id"),
                        resultSet.getString("descripcion")
                );
                estadoRecursos.add(estadoRecurso);
            }
            return estadoRecursos;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }
    public List<Recurso> buscarRecursosPorResponsable (long id){        
        try {
            List<Recurso> recursoList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select recu.* from recursos_responsable rere inner join recurso recu on recu.id=rere.recurso_id where rere.responsable_id =?");
            preparedStatement.setLong(1,id);
            resultSet=preparedStatement.executeQuery();
            Recurso recurso;
            while(resultSet.next()){
                recurso = new Recurso(
                        resultSet.getLong("id"),
                        resultSet.getString("serial"),
                        resultSet.getString("marca"),
                        resultSet.getString("tipo_id"),
                        resultSet.getString("proveedor"),
                        resultSet.getInt("valor_comercial"),
                        resultSet.getString("fecha_compra"),
                        resultSet.getString("estado_id"),
                        resultSet.getString("estado_asignacion")
                );
                recursoList.add(recurso);
            }
            return recursoList;
        }catch (Exception e){
            return null;
        }
    }


    public List<Recurso> buscarRecursoPorSerial(String serial){
        try {
            List<Recurso> recursoList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select * from recurso where serial like '%"+serial+"%'");
            //preparedStatement.setString(1,serial);
            resultSet=preparedStatement.executeQuery();
            Recurso recurso;
            while(resultSet.next()){
                recurso = new Recurso(
                        resultSet.getLong("id"),
                        resultSet.getString("serial"),
                        resultSet.getString("marca"),
                        resultSet.getString("tipo_id"),
                        resultSet.getString("proveedor"),
                        resultSet.getInt("valor_comercial"),
                        resultSet.getString("fecha_compra"),
                        resultSet.getString("estado_id"),
                        resultSet.getString("estado_asignacion")
                );
                recursoList.add(recurso);
            }
            return recursoList;

        }catch (Exception e){

        }finally {
            desconectar();
        }
        return null;
    }

    public List<Recurso> buscarRecursoPorTipo(String tipo) {
        try {
            List<Recurso> recursoList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select * from recurso where tipo_id like '%"+tipo+"%'");
            //preparedStatement.setString(1,tipo);
            resultSet=preparedStatement.executeQuery();
            Recurso recurso;
            while(resultSet.next()){
                recurso = new Recurso(
                        resultSet.getLong("id"),
                        resultSet.getString("serial"),
                        resultSet.getString("marca"),
                        resultSet.getString("tipo_id"),
                        resultSet.getString("proveedor"),
                        resultSet.getInt("valor_comercial"),
                        resultSet.getString("fecha_compra"),
                        resultSet.getString("estado_id"),
                        resultSet.getString("estado_asignacion")
                );
                recursoList.add(recurso);
            }
            return recursoList;

        }catch (Exception e){

        }finally {
            desconectar();
        }
        return null;
    }

    public List<Recurso> buscarRecursoPorMarca(String marca) {
        try {
            List<Recurso> recursoList=new ArrayList<>();

            preparedStatement=getConnection().prepareStatement("select * from recurso where marca like '%"+marca+"%'");
            //preparedStatement.setString(1,marca);
            resultSet=preparedStatement.executeQuery();
            Recurso recurso;
            while(resultSet.next()){
                recurso = new Recurso(
                        resultSet.getLong("id"),
                        resultSet.getString("serial"),
                        resultSet.getString("marca"),
                        resultSet.getString("tipo_id"),
                        resultSet.getString("proveedor"),
                        resultSet.getInt("valor_comercial"),
                        resultSet.getString("fecha_compra"),
                        resultSet.getString("estado_id"),
                        resultSet.getString("estado_asignacion")
                );
                recursoList.add(recurso);
            }
            return recursoList;

        }catch (Exception e){

        }finally {
            desconectar();
        }
        return null;
    }

    public List<Recurso> recursosDisponibles(){
        try {
            List<Recurso> recursoList=new ArrayList<>();

            preparedStatement=getConnection().prepareStatement("select * from recurso where estado_asignacion=0");
            resultSet=preparedStatement.executeQuery();
            Recurso recurso;
            while(resultSet.next()){
                recurso = new Recurso(
                        resultSet.getLong("id"),
                        resultSet.getString("serial"),
                        resultSet.getString("marca"),
                        resultSet.getString("tipo_id"),
                        resultSet.getString("proveedor"),
                        resultSet.getInt("valor_comercial"),
                        resultSet.getString("fecha_compra"),
                        resultSet.getString("estado_id"),
                        resultSet.getString("estado_asignacion")
                );
                recursoList.add(recurso);
            }
            return recursoList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }
    public List<Recurso> asignarRecurso( long recursoId, long responsableId){

        try {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String fechaActual = localDate.format(formatter);
            preparedStatement=getConnection().prepareStatement("insert into recursos_responsable (responsable_id,recurso_id,fecha_asignacion) values(?,?,?) ");
            preparedStatement.setLong(1,responsableId);
            preparedStatement.setLong(2,recursoId);
            preparedStatement.setString(3,fechaActual);
            preparedStatement.executeUpdate();
            preparedStatement=getConnection().prepareStatement("update recurso set estado_asignacion=1 where id=?");
            preparedStatement.setLong(1,recursoId);
            preparedStatement.executeUpdate();
            return this.recursosDisponibles();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR DATA BASE " + e.getStackTrace());

            return null;
        }
        finally {
            desconectar();
        }
    }
    public List<Responsable> buscarResponsablePorId (long id){
        
        try {
            List<Responsable> responsableList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select repo.id,repo.nombre,repo.telefono,tire.descripcion from responsable repo inner join tipo_responsable tire on tire.id=repo.tipo_responsable where repo.id = ?");
            preparedStatement.setLong(1,id);
            resultSet=preparedStatement.executeQuery();
            Responsable  responsable;
            while(resultSet.next()){
                responsable = new Responsable(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                responsableList.add(responsable);
            }
            return responsableList;

        }catch (Exception e){

        }finally {
            desconectar();
        }
        return null;
    }

    public List<Responsable> buscarResponsablePorNombre(String nombre) {
        try {
            List<Responsable> responsableList=new ArrayList<>();
            preparedStatement=getConnection().prepareStatement("select repo.id,repo.nombre,repo.telefono,tire.descripcion from responsable repo inner join tipo_responsable tire on tire.id=repo.tipo_responsable where repo.nombre like '%"+nombre+"%'");
            //preparedStatement.setString(1,nombre);
            resultSet=preparedStatement.executeQuery();
            Responsable  responsable;
            while(resultSet.next()){
                responsable = new Responsable(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                responsableList.add(responsable);
            }
            return responsableList;

        }catch (Exception e){

        }finally {
            desconectar();
        }
        return null;
    }

    public List<Recurso>  agregarRecurso(Recurso recurso) {
        try {
            preparedStatement=getConnection().prepareStatement("insert into recurso(serial,marca,tipo_id,proveedor,valor_comercial,fecha_compra,estado_id,estado_asignacion) values(?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1,recurso.getSerial());
            preparedStatement.setString(2,recurso.getMarca());
            preparedStatement.setString(3,recurso.getTipo());
            preparedStatement.setString(4,recurso.getProveedor());
            preparedStatement.setLong(5,recurso.getValorCOmercial());
            preparedStatement.setString(6,recurso.getFechaDeCompra());
            preparedStatement.setString(7,recurso.getEstado());
            preparedStatement.setString(8,recurso.getEstadoAsignacion());
            preparedStatement.executeUpdate();           
            return this.recursoList();


        }catch (Exception e){
            return null;
        }
    }
    public List<Responsable> agregarResponsable(Responsable responsable){

        try {
            preparedStatement=getConnection().prepareStatement("insert into responsable(nombre,telefono,tipo_responsable) values(?,?,?);");
            preparedStatement.setString(1,responsable.getNombre());
            preparedStatement.setString(2,responsable.getTelefono());
            preparedStatement.setString(3,responsable.getTipo());
            preparedStatement.executeUpdate();
            return this.responsableList();
        }catch (Exception e){
            return null;
        }
    }


    public void actualizarRecurso(Recurso recurso) {
        try {
            preparedStatement=getConnection().prepareStatement("update  recurso set serial=?,"+"marca=?,"+"tipo_id=?,"+"proveedor=?,"+"valor_comercial=?,"+"fecha_compra=?,"+"estado_id=?,"+"estado_asignacion=?"+" where id=?");
            preparedStatement.setString(1,recurso.getSerial());
            preparedStatement.setString(2,recurso.getMarca());
            preparedStatement.setString(3,recurso.getTipo());
            preparedStatement.setString(4,recurso.getProveedor());
            preparedStatement.setLong(5,recurso.getValorCOmercial());
            preparedStatement.setString(6,recurso.getFechaDeCompra());
            preparedStatement.setString(7,recurso.getEstado());
            preparedStatement.setString(8,recurso.getEstadoAsignacion());
            preparedStatement.setLong(9,recurso.getId());
            preparedStatement.executeUpdate();            

        }catch (Exception e){

        }
    }

    public void actualizarResponsable(Responsable responsable){

        try {

            preparedStatement=getConnection().prepareStatement("update responsable set nombre=?,"+"telefono=?"+"tipo_responsable=?"+" where id=?");
            preparedStatement.setString(1,responsable.getNombre());
            preparedStatement.setString(2,responsable.getTelefono());
            preparedStatement.setString(3,responsable.getTipo());
            preparedStatement.setLong(4,responsable.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){

        }
    }

    public List<Recurso> eliminarRecurso(long id){
        try {
            preparedStatement=getConnection().prepareStatement("delete from recursos_responsable where recurso_id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement=getConnection().prepareStatement("delete from recurso where id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            return this.recursoList();
        }catch (Exception e){
            return null;
        }
    }
    public List<Recurso> eliminarRecursoResponsable(long recursoId,long responsableId){
        try {

            preparedStatement=getConnection().prepareStatement("delete from recursos_responsable where recurso_id=?");
            preparedStatement.setLong(1,recursoId);
            preparedStatement.executeUpdate();
            preparedStatement=getConnection().prepareStatement("update recurso set estado_asignacion=0 where id=?");
            preparedStatement.setLong(1,recursoId);
            preparedStatement.executeUpdate();
            return this.buscarRecursosPorResponsable(responsableId);
        }catch (Exception e){
            return null;
        }
    }

    public List<Responsable> eliminarResponsable(long id){
        try {
            List<Recurso> recursosResponsable=this.buscarRecursosPorResponsable(id);
            for (Recurso recursoResponsable:recursosResponsable) {
                preparedStatement=getConnection().prepareStatement("update recurso set estado_asignacion = 0 where id=?");
                preparedStatement.setLong(1,recursoResponsable.getId());
                preparedStatement.executeUpdate();
                
            }
            
            preparedStatement=getConnection().prepareStatement("delete from recursos_responsable where responsable_id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            preparedStatement=getConnection().prepareStatement("delete from responsable where id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return this.responsableList();
        }catch (Exception e){
            return null;
        }
    }


}
