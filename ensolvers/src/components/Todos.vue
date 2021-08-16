<template>

  <section class="src-components-todos">
    
    <h3><router-link id="return" to="/folders">Folders</router-link> > {{folderName}}</h3>
     <table>
      <tr v-for="(task,i) in tasks" :key="i">
        <div v-if="task.id_folder === folderId">
          <td>
            <input type="checkbox"/>
          </td>
          <td>
            {{task.task}}
          </td>
          <td>
            <router-link to="/edit">
              <a @click="sendTaskInfo(task)" href="#">Edit</a>
            </router-link>
          </td>
          <td>
            <a @click="remove(task.id)" href="#">Remove</a>
          </td>
          </div>
        </tr>
     
    </table> 
    <input placeholder="Add new task" id="input" v-model.trim="nameTask"/>
    <button @click="post()">Add</button>
  </section>

</template>

<script lang="js">

import axios from 'axios'

  export default  {
    name: 'src-components-todos',
    props: [],
    mounted () {
      this.start()
    },
    data () {
      return {
        tasks: [],
        folderId: 0,
        lastId: 0,
        folderName: '',
        nameTask: '',
        urlPost: 'http://localhost:8080/tasks/guardar',
        urlGet: 'http://localhost:8080/tasks/listar/',
        urlDeleteTask: 'http://localhost:8080/tasks/eliminar/'
      }
    },
    methods: {
      
       async get() {
        let res = await axios.get(this.urlGet)
        if  (!res.data.length){
           this.tasks = []
        }
        else{
          this.lastId = (res.data[res.data.length-1].id)
          this.tasks = res.data
        } 
      },

      remove(id){
         axios.delete(this.urlDeleteTask + id)
        .then(() => {
          this.get()
        })
      },

       post(){
        let task = {
          id: this.lastId === undefined? 1 : this.lastId + 1,
          id_folder: this.folderId,
          is_done: false,
          task: this.nameTask,
        }
       try {
        axios.post(this.urlPost, task)
            .then(res => {
               if(res.status === 200){
                 this.get()
                 this.nameTask = ''
               }
               else{
                 alert("Failed to post")
               }
            })
      } catch (error) {
        console.log(error)
      }
      },

      sendTaskInfo(task){
        this.$store.state.task = task
      },

      start(){
        this.get()
        this.folderId = this.$store.state.selectedFolder
        this.folderName = this.$store.state.folderName
      }

    },
    computed: {

    }
}


</script>

<style scoped lang="css">
  #return{
    color: black;
  }
  
  a{
    text-decoration: none;
    color: rgb(27, 131, 228);
  }

  table{
    margin: 10px 0;
  }
</style>
