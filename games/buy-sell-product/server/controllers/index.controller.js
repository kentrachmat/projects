/** module exports */
module.exports.home = (req, res) => res.status(301).redirect("/main.html")

module.exports.adminonly = (req, res) => res.render("about")
