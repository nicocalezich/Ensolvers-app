<template>

  <section class="src-components-folders">
    <h3>Folders</h3>
    <table>
      <tr v-for="(folder,i) in folders" :key="i">
        <td>
          - {{folder.name}}
        </td>
        <td>
          <router-link to="/todos">
            <a @click="selectFolder(folder.id,folder.name)" href="#">View items</a>
          </router-link>
        </td>
        <td>
          <a @click="remove(folder.id)" href="#">Remove</a>
        </td>
      </tr>
    </table>  
    <input placeholder="Create a new folder" id="input" v-model.trim="nameFolder"/>
    <button @click="post()">Add</button>
   
  </section>

</template>

<script lang="js">

import axios from 'axios'

  export default  {
    name: 'src-components-folders',
    props: [],
    mounted () {
      this.get()
      this.getTodos()
    },
    data () {
      return {
        folders: [],
        tasks: [],
        id: 1,
        nameFolder: '',
        urlPost: 'http://localhost:8080/folders/guardar',
        urlGet: 'http://localhost:8080/folders/listar',
        urlDelete: 'http://localhost:8080/folders/eliminar/',
        urlDeleteTask: 'http://localhost:8080/tasks/eliminar/',
        lastId: 0
      }
    },
    methods: {

    post(){
        let folder = {
          id: this.lastId === undefined? 1 : this.lastId + 1,
          name: this.nameFolder,
        }
       try {
        axios.post(this.urlPost, folder)
            .then(res => {
               if(res.status === 200){
                 this.get()
                 this.nameFolder = ''
               }
               else{
                 alert("Failed to post")
               }
            })
      } catch (error) {
        console.log(error)
      }
    },

     async getTodos() {
        let res = await axios.get('http://localhost:8080/tasks/listar/')
        if  (!res.data.length){
           this.tasks = []
        }
        else{
          this.lastId = (res.data[res.data.length-1].id)
          this.tasks = res.data
          console.log(this.tasks)
        } 
      },

    async get() {
        let res = await axios.get(this.urlGet)
        if  (!res.data.length){
           this.folders = []
        }
        else{
          this.lastId = (res.data[res.data.length-1].id)
          this.folders = res.data
        } 
      },

      remove(id) {
      axios.delete(this.urlDelete + id)
      .then(() => {
        this.removeTasksById(id)
        this.get()
      })
    },

     removeTasksById(id) {
      let taskByFolderId = this.tasks.filter(task => task.id_folder === id)
      taskByFolderId.forEach( task =>
        axios.delete(this.urlDeleteTask + task.id)
        .then(() => {
        })
      )
    },

    selectFolder(id,name){
      this.$store.state.selectedFolder = id
      this.$store.state.folderName = name
    }

  },
  computed: {

  }
}


</script>

<style scoped lang="css">
  .src-components-folders {
  
  }

  a{
    text-decoration: none;
    color: rgb(27, 131, 228);
  }

  table{
    margin: 10px 0;
  }

</style>
