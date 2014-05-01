#!/usr/bin/env python

# If we list all the natural numbers below 10 that are multiples of 3 or 5, we
# get 3, 5, 6 and 9. The sum of these multiples is 23.
#
# Find the sum of all the multiples of 3 or 5 below 1000.



def sum_multiples_of(multiples, n) :
    """ Return sum of all integers in [1..n) which are multiples of all m in
    multiples. """

    sum = 0
    for i in range(1, n) :
        for m in multiples :
            if (i%m == 0) :
                sum += i
                break

    return sum

if __name__ == "__main__" :
    multiples = [3,5]
    n = 1000
    sum = sum_multiples_of(multiples, n)
    print ("All multiples of %s from %d to %d add up to %d" %
            (str(multiples), 1, n-1, sum))
