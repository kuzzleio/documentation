<template>
  <div class="catalog">
    <NavLink
      v-for="(entry, index) in entries"
      :key="entry.id"
      :path="entry.path"
      class="catalog__tile"
      :class="{
        'catalog__tile--featured': index === 0,
        'catalog__tile--private': entry.private,
      }"
    >
      <img
        :src="$withBase(entry.icon)"
        alt=""
        class="catalog__logo"
        width="56"
        height="56"
      />
      <div class="catalog__body">
        <div class="catalog__head">
          <span class="catalog__name">{{ entry.text }}</span>
          <span v-if="entry.private" class="catalog__badge">
            <font-awesome-icon icon="fa-solid fa-lock" />
            Customer access
          </span>
        </div>
        <p class="catalog__description">{{ entry.description }}</p>
        <p v-if="entry.private && !signedIn$" class="catalog__note">
          Reserved for Kuzzle customers: sign in with your Kuzzle PaaS account.
        </p>
        <span class="catalog__action">
          {{ entry.private && !signedIn$ ? 'Sign in' : 'Open' }}
          <span aria-hidden="true">→</span>
        </span>
      </div>
    </NavLink>
  </div>
</template>

<script>
import { useSignedIn } from '../auth';
import { CATALOG } from '../products';

/**
 * Home page: one tile per product, private ones flagged (ADR-0004).
 *
 * The tile only informs: a private section is protected by CloudFront, which
 * serves the sign-in page to anyone without a session, whatever the entry
 * point.
 */
export default {
  name: 'ProductCatalog',
  setup() {
    return { signedIn$: useSignedIn() };
  },
  computed: {
    entries() {
      return CATALOG;
    },
  },
};
</script>
