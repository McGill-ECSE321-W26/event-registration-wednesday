import { createRouter, createWebHistory } from 'vue-router'
import EventView from '../views/EventView.vue'
import PersonView from "@/views/PersonView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'events',
      component: EventView,
    },
    {
      path: '/person',
      name: 'person',
      component: PersonView,
    },
  ],
})

export default router
