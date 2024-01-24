"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#FRANCAIS
fr = {}
fr['login_title'] = "Multi Fun Jeu Login"
fr['login_user'] = "Nom D\'utilisateur"
fr['login_pass'] = "Mot de Passe"
fr['login_new_account'] = "Nouveau Compte"
fr['login_acc_block'] = "votre compte a été bloqué, veuillez contacter l'administrateur pour le débloquer"
fr['login_no_found'] = "Nous ne trouvons pas votre compte dans notre base de données"
fr['login_nok'] = "Mot de passe / nom d\'utilisateur incorrect ! \nTentative"
fr['login_ok'] = "Connexion Réussie"
fr['login'] = "Connexion"

fr['register_title'] = "Enregistrement d'un nouvel utilisateur"
fr['register_lang'] = ""
fr['register_save'] = "Sauvgarder"
fr['register_nok'] = "l\'entrée ne peut pas être vide"
fr['register_ok'] = "le compte a été créé ! \nveuillez vérifier votre email pour plus d'informations"
fr['register_fullname'] = "Nom Complet"
fr['register_email'] = "Email"
fr['register_nok_user'] = "L'utilisateur a déjà été utilisé"
fr['register_nok_pass'] = "La taille du mot de passe doit être supérieure à 4"
fr['register_nok_email'] = "Email incorrect"

fr['notif_exit'] = "Voulez-vous quitter ce programme ?"
fr['notif_confirm'] = "Confirmation pour quitter"

fr['email_registration_subject'] = "[%s] Nouveau compte" % fr['login_title']
fr['email_registration_text'] = """Bonjour %s,\n\nMerci de vous etre inscrit a notre projet ! \n
notre projet consiste en differents types de jeux que vous pouvez lancer tels que le jeu de serpent, le jeu de voiture, le pong, etc. \n
n'hesitez pas a contacter l'administrateur dans la boîte de discussion si vous avez besoin d'aide. voici le nom d'utilisateur et le mot de passe que vous avez saisi lors de notre inscription a notre programme : \n\n
nom d'utilisateur : %s\n
mot de passe : %s\n

Sincerement,\n
Administrateur de Multi Fun Jeu"""  

#ANGLAIS
eng = {}
eng['login_title'] = "Multi Fun Game Login"
eng['login_user'] = "Username"
eng['login_pass'] = "Password"
eng['login_new_account'] = "New Account"
eng['login_acc_block'] = "Your account has been blocked, please contact the admin to unblock"
eng['login_no_found'] = "We can't find your account in our database"
eng['login_nok'] = "Username / Password incorrect ! \nTentative"
eng['login_ok'] = "Login Success"
eng['login'] = "Login"

eng['register_title'] = "New User Registration"
eng['register_fullname'] = "Fullname"
eng['register_email'] = "Email"
eng['register_save'] = "Save"
eng['register_nok'] = "Input cannot be empty"
eng['register_ok'] = "The account has been created !\nplease check your email for more information"
eng['register_nok_user'] = "Username have been used"
eng['register_nok_pass'] = "Password size must be bigger than 4"
eng['register_nok_email'] = "Email format incorrect"

eng['notif_exit'] = "Do you want to quit this program ?"
eng['notif_confirm'] = "Confirmation to quit"

eng['email_registration_subject'] = "[%s] New Account" % eng['login_title']
eng['email_registration_text'] = """Hello %s,\n\nThank you for registering into our project ! \n 
our project consist different kind of game that you can enjoy such as snake game, car game, pong, etc. \n
feel free to contact admin in the chat box if you need help. here's the username and password that you entered upon our registration in our program : \n\n
username : %s\n
password : %s\n

Sincerely,\n
Multi Fun Game Admin"""
