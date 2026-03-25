<template>
  <main>
    <h1>Persons</h1>
    <section>
      <h2>Create a new person</h2>
      <form @submit.prevent="createPerson">
        <label for="name">Name:</label>
        <input id="name" v-model="newPersonName" required />

        <label for="email">Email:</label>
        <input id="email" v-model="newPersonEmail" type="email" required />

        <label for="password">Password:</label>
        <input id="password" v-model="newPersonPassword" type="password" required />

        <button type="submit" :disabled="!isPersonValid()">Create Person</button>
        <button type="button" @click="clearInputs()">Clear</button>
      </form>
      <h2>Query person information by id</h2>
      <form @submit.prevent="queryPerson">
        <label for="personId">Person ID:</label>
        <input id="personId" v-model="personId" required />

        <button type="submit">Get Person Info</button>
      </form>
      <label for="personName">Name:</label>
      <p>{{ queriedPerson.name }}</p>
      <label for="personEmail" >Email:</label>
      <p>{{ queriedPerson.email }}</p>
    </section>
  </main>
</template>

<script>
import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8081"
});

export default {
  name: "persons",
  data() {
    return {
      queriedPerson: {},
      personId: "",
      newPersonName: "",
      newPersonEmail: "",
      newPersonPassword: ""
    };
  },
  methods: {
    createPerson() {
      axiosClient.post("/person", {
        name: this.newPersonName,
        email: this.newPersonEmail,
        password: this.newPersonPassword
      }).then(response => {
        alert("New person created with ID" + response.data.id);
      }).catch(error => {
        alert(error.response.data.errors);
      })
      this.clearInputs();
    },
    queryPerson() {
      axiosClient.get(`/person/${this.personId}`)
          .then(response => {
            this.queriedPerson = response.data;
          })
          .catch(error => {
            console.error("There was an error fetching the person data!", error);
          });
    },
    clearInputs() {
      this.newPersonName = "";
      this.newPersonEmail = "";
      this.newPersonPassword = "";
    },
    isPersonValid() {
      return (
          this.newPersonName.trim() !== "" &&
          this.newPersonEmail.trim() !== "" &&
          this.newPersonPassword.trim() !== ""
      );
    }
  }
};
</script>