from tkinter import *
import random
import tkinter.ttk


def Fill(color):
    global index

    if index < 3:
        index += 1
        circle = circles[index + 4 * guess]
        c.itemconfig(circle, fill=color)
        colors = {'red': 1, '#ff8200': 2, 'yellow': 3, '#00fa2a': 4, '#1fc1ff': 5, '#a01fff': 6}
        guessed_colors.append(colors.get(color))
        clear.config(state='active')

    if index == 3:
        check.config(state='active')
    else:
        check.config(state='disabled')



def Clear():
    global index

    if index > -1:
        guessed_colors.pop()
        circle = circles[index + 4 * guess]
        c.itemconfig(circle, fill='white')
        index -= 1
        check.config(state='disabled')



def Check():
    global guess, index
    print("S: ", secret)
    print("G: ", guessed_colors)
    SetAB(guessed_colors, secret)
    check.config(state='disabled')
    clear.config(state='disabled')
    guessed_colors.clear()
    guess += 1
    index = -1


def SetAB(guessed_colors, secret):
    a_value, b_value = 0, 0

    for i in range(1, 7):
        color = i
        correct_color, correct_position, guess_counter, secret_counter = 0, 0, 0, 0

        for j in range(4):
            if guessed_colors[j] == color:
                guess_counter += 1
            
            if secret[j] == color: 
                secret_counter += 1

                for k in range(4):
                    if guessed_colors[k] == color:
                        correct_color += 1

                        if j == k:
                             correct_position += 1
    
        if secret_counter == 0:
             continue

        constant = max(guess_counter, secret_counter)
        a_value += correct_position
        b_value += (correct_color / constant) - correct_position

    a_text = "a: " + str(a_value)
    b_text = "b: " + str(int(b_value))
    a = ab[guess][0]
    b = ab[guess][1]
    a.config(text=a_text)
    b.config(text=b_text)

    if a_value == 4:
        Label(root, text="You guessed the secret!", bg='white', fg='green').place(x=330, y=365)
        Stop()

    elif guess == 9:
        Label(root, text="     It's ok. Play again.", bg='white', fg='red').place(x=330, y=365)
        Stop()


def Stop():
    red.config(state='disabled')
    yellow.config(state='disabled')
    orange.config(state='disabled')
    green.config(state='disabled')
    blue.config(state='disabled')
    purple.config(state='disabled')

    
    colors = {1: 'red', 2: '#ff8200', 3: 'yellow', 4: '#00fa2a', 5: '#1fc1ff', 6: '#a01fff'}
    c.create_oval(295, 440, 315, 460, fill=colors.get(secret[0]))
    c.create_oval(320, 440, 340, 460, fill=colors.get(secret[1]))
    c.create_oval(345, 440, 365, 460, fill=colors.get(secret[2]))
    c.create_oval(370, 440, 390, 460, fill=colors.get(secret[3]))
    Label(root, text="SECRET:", bg='white').place(x=370, y=410)


# to handle the circles
index = -1
guess = 0
circles = list()
guessed_colors = list()
ab = list()
secret = [random.randrange(1, 7) for i in range(4)]


# root configuration
# resizable(width=boolean, height=boolean) method determines if the window can be locked or not.
root = Tk()
root.title('MasterMind')
root.configure(bg='white')
root.geometry('500x600')
root.resizable(False, False)


# creating a canvas to draw the circles in it
# Canvas(...).pack() raises an error on using the create_oval() method.
# in order to use create_oval() method, we must call pack() in a separate line.
c = Canvas(root, background='white', highlightthickness=0, height=600, width=400)
c.pack()


# drawing a line to separate different parts of the game.
c.create_line(230, 0, 230, 580)


# game description
name = Label(root, text="MasterMind", background='white', font=(36)).place(x=350, y=80)
box = Message(text=" - Try to guess the secret by choosing the colors from the color palette. \n\n-To clear a circle, click on \"Clear\". \n\na: correct color and correct position \n\nb: correct color but wrong position", 
             background='white', highlightthickness=1, width=170).place(x=308, y=130)


# creating circles and labels
for i in range(10):
    # Label(...).place() raises an error on using the config() method.
    # in order to use config() method, we must call place() function in a separate line.
    # anchor='w' aligns the text to left(west). (not the label itself).
    delta = 50 * i
    text = "Guess " + str(i+1)
    guess_label = Label(root, text=text, bg='white', width=6, height=2).place(x=0, y=2+delta)
    a_label = Label(root, text='a: ', bg='white', width=5, anchor='w')
    a_label.place(x=220, y=delta)
    b_label = Label(root, text='b: ', bg='white', width=5, anchor='w')
    b_label.place(x=220, y=20+delta)
    ab.append([a_label, b_label])

    for j in range(4):
        delta_x = 35 * j
        circle = c.create_oval(15+delta_x, 5+delta, 45+delta_x, 35+delta)
        circles.append(circle)


# creating buttons
# note: use 'command=lambda: func()' or 'command=func' if func() has no arguments. 'command = func()' is incorrect.
red = Button(root, width=3, relief='groove', background='red', command=lambda: Fill('red'))
red.place(x=40, y=510)
orange = Button(root, width=3, relief='groove', background='#ff8200', command=lambda: Fill('#ff8200'))
orange.place(x=80, y=510)
yellow = Button(root, width=3, relief='groove', background='yellow', command=lambda: Fill('yellow'))
yellow.place(x=120, y=510)
green = Button(root, width=3, relief='groove', background='#00fa2a', command=lambda: Fill('#00fa2a'))
green.place(x=40, y=545)
blue = Button(root, width=3, relief='groove', background='#1fc1ff', command=lambda: Fill('#1fc1ff'))
blue.place(x=80, y=545)
purple = Button(root, width=3, relief='groove', background='#a01fff', command=lambda: Fill('#a01fff'))
purple.place(x=120, y=545)

check = tkinter.ttk.Button(root, text='Check', command=Check, state='disabled')
check.place(x=180, y=511)
clear = tkinter.ttk.Button(root, text='Clear', command=Clear, state='disabled')
clear.place(x=180, y=546)
exit = tkinter.ttk.Button(root, text='Exit', command=lambda: root.destroy()).place(x=355, y=546)


root.mainloop()