import random

print("--- Rock Paper Scissors Game ---")

rounds = ''
choice_list = ["r", "p", "s"]
computer_score, player_score = 0, 0

while type(rounds) != int:
    try:
        rounds = int(input("How many rounds would you like to play?\n"))
    except:
        print("Please provide a correct number.")

for i in range(rounds):
    player_choice = input("Rock, paper or scissors [r/p/s]?\n")

    while player_choice not in choice_list:
        print("Please provide letter 'r' for rock, 'p' for paper or 's' for scissors.")
        player_choice = input("Rock, paper or scissors [r/p/s]?\n")
    
    computer_choice = random.choice(choice_list)

    print("You: " + player_choice + " | Computer: " + computer_choice)

    match(player_choice):
        case("r"):
            match(computer_choice):
                case("r"):
                    print("This round is a tie")
                case("p"):
                    print("You lost this round!")
                    computer_score += 1
                case("s"):
                    print("You won this round!")
                    player_score += 1
        case("p"):
            match(computer_choice):
                case("r"):
                    print("You won this round!")
                    player_score += 1
                case("p"):
                    print("This round is a tie")
                case("s"):
                    print("You lost this round!")
                    computer_score += 1
        case("s"):
            match(computer_choice):
                case("r"):
                    print("You lost this round!")
                    computer_score += 1
                case("p"):
                    print("You won this round!")
                    player_score += 1
                case("s"):
                    print("This round is a tie")

print("[Game summary] Your points: " + str(player_score) + " | Computer points: " + str(computer_score))

if player_score > computer_score:
    print("You won.")
elif player_score < computer_score:
    print("Computer won.")
else:
    print("It was a tie.")