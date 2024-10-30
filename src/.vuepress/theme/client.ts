import { defineClientConfig } from '@vuepress/client';

import Layout from './Layout.vue';
import NotFound from './NotFound.vue';
import './styles/main.scss';

export default defineClientConfig({
  layouts: {
    Layout,
    NotFound,
  },
});
