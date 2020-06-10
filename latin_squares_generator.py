
def generate_latin_squares(n, file_path):
    f = open(file_path, "w")
    quant = n
    init = 2
    lines = [str(quant) + '\n']
    for i in range(init, n + init):
        latin_square = _generate_latin_square(i)
        square_line = " ".join(map(str, latin_square))
        line = str(i) + " " + square_line
        lines.append(line + '\n')
    f.writelines(lines)
    f.close()


# Function to prn x n Latin Square
def _generate_latin_square(n):

    # A variable to control the
    # rotation point.
    k = n + 1
    latin_square = []
    # Loop to prrows
    for i in range(1, n + 1, 1):

        # This loops runs only after first
        # iteration of outer loop. It prints
        # numbers from n to k
        temp = k
        while (temp <= n) :
            latin_square.append(temp)
            temp += 1

        # This loop prints numbers
        # from 1 to k-1.
        for j in range(1, k):
            latin_square.append(j)
            #print(j, end = " ")

        k -= 1
    return latin_square

# entry point
if __name__ == '__main__':
    file_path = "C:/Users/AA478QW/Desktop/Unq/Concurrente/latin_squares.txt"
    generate_latin_squares(100, file_path)