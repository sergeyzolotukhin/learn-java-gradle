"""
# How to install package

.\venv\Scripts\activate
pip install termcolor
deactivate

# Open question

    How to activate venv automatically ?
    How to install package automatically ?

"""

from colorama import init
from colorama import Fore, Back, Style

init()

menu_options = {
    1: Fore.WHITE + 'Type Loader' + Fore.RESET,
    2: Fore.WHITE + '128 BASIC' + Fore.RESET,
    3: Fore.WHITE + 'Calculator' + Fore.RESET,
    4: Fore.WHITE + 'TR-DOS' + Fore.RESET,
    5: Fore.GREEN + 'Exit' + Fore.RESET,
}


def print_menu():
    print('128            ////')
    for key in menu_options.keys():
        print(key, menu_options[key])


def option1():
    print('Handle option ' + Fore.RED + '\'Option 1\'' + Fore.RESET)


def option2():
    print('Handle option ' + Fore.YELLOW + '\'Option 2\'' + Fore.RESET)


def option3():
    print('Handle option ' + Fore.BLUE + '\'Option 3\'' + Fore.RESET)


if __name__ == '__main__':
    while True:
        print_menu()
        option = ''
        try:
            option = int(input('Enter your choice: '))
        except:
            print('Wrong input. Please enter a number ...')

        if option == 1:
            option1()
        elif option == 2:
            option2()
        elif option == 3:
            option3()
        elif option == 4:
            option3()
        elif option == 5:
            print(Fore.GREEN + 'Thanks message before exiting' + Fore.RESET)
            exit()
        else:
            print('Invalid option. Please enter a number between 1 and 4.')
