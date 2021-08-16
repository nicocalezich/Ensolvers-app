<template>

  <section class="src-components-edit">
    <h3>Editing task "{{task.task}}"</h3>
    <input v-model.trim="nameTask"/>
    <div>
      <button @click="put()">Save</button>
      <router-link to="/todos">
        <button>Cancel</button>
      </router-link>  
    </div>
    
  </section>

</template>

<script lang="js">

import axios from 'axios'

  export default  {
    name: 'src-components-edit',
    props: [],
    mounted () {
      this.getTask()
    },
    data () {
      return {
        urlPut: 'http://localhost:8080/tasks/guardar',
        nameTask: '',
        task: {}
      }
    },
    methods: {

      put(){
         let editedTask = {
          id: this.task.id,
          id_folder: this.task.id_folder,
          is_done: this.task.is_done,
          task: this.nameTask,
        }
       try {
        axios.post(this.urlPut, editedTask)
            .then(res => {
               if(res.status === 200){
                  this.$router.push('/todos')  
               }
               else{
                 alert("Failed to post")
               }
            })
      } catch (error) {
        console.log(error)
      }
    },

    getTask(){
      this.task = this.$store.state.task
      this.nameTask = this.task.task
    }

    },
    computed: {

    }
}


</script>

<style scoped lang="css">
  .src-components-edit {

  }
</style>
