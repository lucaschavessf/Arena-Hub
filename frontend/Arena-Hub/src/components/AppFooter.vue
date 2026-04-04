<template>
  <footer class="footer">
    <div class="footer-inner">
      <p>© 2026 Arena Pernambuco · Bilheteria Digital. Todos os direitos reservados.</p>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  width: 100%;
  background: var(--navy);
  border-top: 1px solid rgba(255,255,255,0.05);
  padding: 30px 0;
}
.footer-inner {
  /* ALINHAMENTO MESTRE */
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 20px;
  text-align: center;
}
.footer p {
  font-size: 0.75rem;
  color: var(--muted);
  letter-spacing: 0.3px;
}
</style>