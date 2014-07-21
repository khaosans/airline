import os
import time


def main():

    os.chdir("..")
    time.sleep(1)
    os.system('mvn package')
    time.sleep(3)
    os.chdir('target')


    os.system('chmod +x airline-1.0-SNAPSHOT.jar')



    print '*****  Test 1: No arguments'
    os.system('java -jar airline-1.0-SNAPSHOT.jar   ')
    
    print '\n\n\n'

    print '*****  Test 2: Your README'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -README')
    print '\n\n\n'

    print '*****  Test 3: Flight number isn\'t an integer'
    os.system('java -jar airline-1.0-SNAPSHOT.jar Test3 NUMBER PDX 03/03/2014 12:00 ORD 03/03/2014 16:00') 

    print '\n\n\n'

    print '*****  Test 4: Departure time is malformatted'
    os.system('java -jar airline-1.0-SNAPSHOT.jar Test4 123 PDX 03/03/2014 12:XX ORD 03/03/2014 16:00')
 
    print '\n\n\n'

    print '*****  Test 5: Arrival time is malformatted'
    output5 = os.system('java -jar airline-1.0-SNAPSHOT.jar Test4 123 PDX 03/03/2014 12:XX ORD 03/03/2014 16:00')
    
    print '\n\n\n'  

    print '*****  Test 6: Unknown command line argument'

    os.system('java -jar airline-1.0-SNAPSHOT.jar Test6 123 PDX 03/03/2014 12:00 ORD 04/04/2014 16:00 fred')
    print '\n\n\n' 

    print '*****  Test 7: Printing out a flight'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -print Test7 123 PDX 03/03/2014 12:00 ORD 05/04/2014 16:00')
    print '\n\n\n' 

    print '*****  Test 8: Multi-word airline name'

    os.system('java -jar airline-1.0-SNAPSHOT.jar -print "Test 8" 123 PDX 03/03/2014 12:00 ORD 09/04/2014 16:00')
    print '\n\n\n' 

    print '*****  Test 9: Missing departure time'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -print Test9 123 PDX 03/03/2014 12:00 ORD 09/09/2014')
    print '\n\n\n' 


    print '////////////////////////////////////////////Project 2//////////////////////////////////////////////'

    print 'Test 6: Unknown command line argument'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -textFile khaosans/khaosans-x.txt Test6 123 PDX 03/03/2014 12:00 ORD 01/04/2014 16:00 fred')
    print '\n\n\n' 

    print '*****  Test 7: Starting a new airline file'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -textFile khaosans/khaosans.txt -print Project2 700 PDX 01/07/2014 07:00 MHT 01/17/2014 17:00')
    print '\n\n\n'

    print '*****  Test 8: Using an existing airline file'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -textFile khaosans/khaosans.txt -print Project2 800 MHT 01/08/2014 08:00 BTV 01/08/2014 18:00')
    print '\n\n\n'

    print '*****  Test 9: Different airline name'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -textFile khaosans/khaosans.txt DIFFERENT 900 BTV 01/09/2014 09:00 CLE 02/04/2014 16:00')
    print '\n\n\n'

    print '*****  Test 10: Malformatted text file'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -textFile khaosans/bogus.txt Project2 1000 CLE 01/10/2014 10:00 CHA 01/20/2014 20:00')
    print '\n\n\n'

    print '////////////////////////////////////////////Project 3//////////////////////////////////////////////'

    print '*****  Test 1: Pretty file'
    os.system('java -jar airline-1.0-SNAPSHOT.jar -print -pretty prettyfile.txt -textFile khaosans/khaosans2.txt Project3 123 PDX 03/03/2014 12:00 ORD 05/04/2014 16:00')
    print '\n\n\n'

if ( __name__ == "__main__" ):
    main()
