
(() => {
  const form = document.getElementById("queue-form");
  const frame = document.querySelector('iframe[name="output"]');
  const frameBox = document.getElementById("result-frame");
  const status = document.getElementById("result-status");
  const empty = document.getElementById("result-empty");
 
  let currentLabel = null;
 
  // Libellé du guichet sélectionné, par exemple "Guichet 2"
  function selectedCounterLabel() {
    const checked = form.querySelector('input[name="id"]:checked');
    if (!checked) return "";
    const label = form.querySelector(`label[for="${checked.id}"]`);
    return label ? label.textContent.trim() : "";
  }
 
  // Au clic sur une action : afficher l'état de chargement
  form.addEventListener("submit", (event) => {
    const button = event.submitter;
    const action = button && button.dataset.label ? button.dataset.label : "Liste des tickets";
 
    currentLabel = `${action}, ${selectedCounterLabel().toLowerCase()}`;
 
    empty.hidden = true;
    frameBox.setAttribute("aria-busy", "true");
    status.textContent = `Chargement : ${currentLabel}`;
  });
 
  // Quand la réponse du serveur est affichée
  frame.addEventListener("load", () => {
    if (!currentLabel) return; // chargement initial de l'iframe vide
 
    frameBox.setAttribute("aria-busy", "false");
    status.textContent = currentLabel.charAt(0).toUpperCase() + currentLabel.slice(1);
 
    // Harmoniser la typographie de la réponse (possible car même origine)
    try {
      const doc = frame.contentDocument;
      if (doc && doc.body) {
        doc.documentElement.style.background = "transparent";
        doc.body.style.margin = "0";
        doc.body.style.padding = "16px";
        doc.body.style.font = '400 0.9375rem/1.6 "IBM Plex Sans", "Segoe UI", system-ui, sans-serif';
        doc.body.style.color = "#15221a";
        doc.body.style.whiteSpace = "pre-wrap";
        doc.body.style.wordBreak = "break-word";
      }
    } catch (error) {
      // Réponse d'une autre origine : on laisse le rendu par défaut
    }
  });
})();
 