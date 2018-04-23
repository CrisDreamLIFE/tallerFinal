<template>
    <form id="app" @submit.prevent>
      <div class="center">
        <p v-if="errors.length">
          <b>Problema(s):</b>
          <ul>
            <li v-for="error in errors">{{ error }}</li>
          </ul>
        </p>
        <div>
          <p v-if="exito">
            <b>Usuario ingresado exitosamente!</b>
          </p>
        </div>
        <p>
          <h2>{{title}}</h2>
        </p>
          <div>
            <input v-model="nombre" placeholder="Nombre"><br><br>
            <input v-model="apellido" placeholder="Apellido">
          </div>
        <p>
          <input type="submit" value="Agregar" @click="checkForm">  
        </p>
      </div>
    </form>
</template>
<script>
export default {
  data(){
    return{
      title:'Agregar',
      errors:[],
      nombre:null,
      apellido:null,
      idActual:0,
      exito:false
    }
  },
  mounted:function(){
    this.$http.get('http://localhost:8081/sakila-spring-backend/actors')
    .then(response=>{
      var actores = response.body;
      this.idActual = actores.length + 1;
     console.log('id a ingresar: ', this.idActual);
    }, response=>{
       console.log('error cargando lista');
    })
  },
    methods:{
      checkForm:function(e) {
        if(this.nombre && this.apellido){
          var fecha = new Date();
          var datos = {
            id: this.idActual,
            firstName: this.nombre,
            lastName: this.apellido,
            lastUpdate: fecha.getTime()
          }
         this.$http.post('http://localhost:8081/sakila-spring-backend/actors', datos)
                    .then(function() {
                        console.log('usuario almacenado exitosamente');
                        this.idActual++;
                        this.exito = true;
                    })
                    .catch(function() {
                        console.log('error');
                    });
          console.log(this.nombre);
          console.log(this.apellido);
          this.nombre = "";
          this.apellido = "";
          return true;
        }
        this.errors = [];
        if(!this.nombre) this.errors.push("Debe ingresar un nombre.");
        if(!this.apellido) this.errors.push("Debe ingresar un apellido.");
        e.preventDefault();
      }
    }

}
</script>