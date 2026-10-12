import { computed, onMounted, ref, watch } from 'vue';
import { useRoute, useSiteData } from 'vuepress/client';

import {
  PRODUCT_STORAGE_KEY,
  Product,
  absolutePath,
  findEntry,
  PRODUCTS,
  resolveProduct,
} from './products';

/**
 * Product the header should display for the current page.
 *
 * Product pages resolve from their path. Shared sections (SDKs, Plugins,
 * Modules) are built as their own VuePress instances, so reaching them is a
 * full page load: the product the visitor comes from is remembered in
 * localStorage, which is only read once mounted. The server-rendered header
 * therefore shows the fallback product until hydration.
 */
export const useCurrentProduct = () => {
  const route = useRoute();
  const site = useSiteData();
  const rememberedId = ref<string | null>(null);

  const path = computed(() => absolutePath(site.value.base, route.path));
  const product = computed<Product | undefined>(() =>
    resolveProduct(path.value, rememberedId.value)
  );

  onMounted(() => {
    try {
      rememberedId.value = localStorage.getItem(PRODUCT_STORAGE_KEY);
    } catch {
      // Storage may be unavailable (privacy mode), keep the fallback.
    }

    // Remember the product of every product page the visitor lands on or
    // navigates to, shared sections never overwrite it.
    watch(
      path,
      (current) => {
        const own = findEntry(PRODUCTS, current);

        if (!own || own.id === rememberedId.value) {
          return;
        }

        rememberedId.value = own.id;

        try {
          localStorage.setItem(PRODUCT_STORAGE_KEY, own.id);
        } catch {
          // Same as above.
        }
      },
      { immediate: true }
    );
  });

  return product;
};
