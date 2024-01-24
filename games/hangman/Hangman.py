#Author : Kent

def choose_word():
    word = ""
 
    while word == "":
        word = input("Letter : ")
 
    return word
 
def encrypt_word(word, found_chars):
    encrypt = ""
 
    for char in word:
        if char in found_chars:
            encrypt += char
        else :
            encrypt += "*"
 
    return encrypt
 
def check_letter(word, found_chars, letter):
    if letter in word:
        found_chars.append(letter)
        return True
 
    return False
 
def check_win(word, found_chars):
    encrypted = encrypt_word(word,found_chars)
 
    for char in encrypted:
        if char == "*":
            return False
 
    return True
 
def start_game():
    word = choose_word()
    found_chars = []
    won = False
    max_tries = 10
    tries = 0
 
    while not won:
        if tries == max_tries:
            print("\nBetter luck next time :) ")
            print("Thank you for playing the game !\n")
            return
 
        print("\n", encrypt_word(word, found_chars))
        print("Attempt : {}/{}\n".format(tries + 1, max_tries))
 
        letter = ""
        while letter == "":
            letter = input("\nType a letter : ")
 
        if len(letter) == 1:
            if not check_letter(word, found_chars, letter):
                tries += 1
            else:
                won = check_win(word, found_chars)
        elif letter == word:
            won = True
        else:
            tries += 1
   
    print("\nCongratulations are the winner ! the word was : ", word)
 
start_game()
