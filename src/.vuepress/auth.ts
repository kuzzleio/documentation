import { onMounted, ref } from 'vue';

/**
 * Sign-in to the private sections (ADR-0002, ADR-0004).
 *
 * The `/_auth/*` routes are served by a Lambda behind CloudFront, outside of
 * VuePress (static-sites-infra, `documentation/auth`). Access is enforced by
 * CloudFront through signed cookies, which are `HttpOnly`: the front cannot
 * see them. The Lambda sets `docs_session=1` next to them, readable from JS,
 * only to adapt the display. It is never a proof of access.
 */
export const SESSION_COOKIE = 'docs_session';
export const LOGOUT_PATH = '/_auth/logout';

export const hasSessionCookie = (cookies: string) =>
  cookies.split(';').some((cookie) => cookie.trim() === `${SESSION_COOKIE}=1`);

/**
 * Whether the visitor is signed in. Cookies are only read once mounted, so
 * the server-rendered page always shows the signed-out state.
 */
export const useSignedIn = () => {
  const signedIn = ref(false);

  onMounted(() => {
    signedIn.value = hasSessionCookie(document.cookie);
  });

  return signedIn;
};
