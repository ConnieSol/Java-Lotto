import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//Αναπτύξτε ένα πρόγραμμα σε Java που να διαβάζει από ένα αρχείο ακέραιους
//αριθμούς (το αρχείο πρέπει να περιέχει περισσότερους από 6 αριθμούς και το πολύ
//49 αριθμούς) με τιμές από 1 έως 49. Τους αριθμούς αυτούς τους εισάγει σε ένα
//πίνακα, τον οποίο ταξινομεί (π.χ. με την Arrays.sort()). Στη συνέχεια, το πρόγραμμα
//παράγει όλες τις δυνατές εξάδες (συνδυασμούς 6 αριθμών). Ταυτόχρονα και αμέσως
//μετά την παραγωγή κάθε εξάδας ‘φιλτράρει’ κάθε εξάδα ώστε να πληροί τα
//παρακάτω κριτήρια: 1) Να περιέχει το πολύ 4 άρτιους, 2) να περιέχει το πολύ 4
//περιττούς, 3) να περιέχει το πολύ 2 συνεχόμενους, 4) να περιέχει το πολύ 3 ίδιους
//λήγοντες, 5) να περιέχει το πολύ 3 αριθμούς στην ίδια δεκάδα.
//Τέλος, εκτυπώνει τις τελικές εξάδες σε ένα αρχείο με όνομα της επιλογής σας και
//κατάληξη.txt.


public class project01Lotto {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            int[] nums = getNumsFromFile();
            Arrays.sort(nums);
            makeSixs(nums);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Input mismatch: " + e.getMessage());
        }

    }




    //διαβάζει ακεραίους από αρχείο και τους επιστρέφει σε ταξινομημένο πίνακα
    public static int[] getNumsFromFile() throws FileNotFoundException, InputMismatchException {
        File fileNumbers = new File("D:\\ΚΩΝΣΤΑΝΤΙΝΑ\\java\\numbers.txt");
        int nums;
        int[] numsArray = new int[49]; // νέος πίνακας 49 θέσεων για αποθήκευση αριθμών που θα διαβαστούν από το αρχείο
        int count = 0; // μετρητής για να παρακολουθεί τους αριθμούς που έχουμε διαβάσει και αποθηκεύσει στον πίνακα


        try (Scanner in = new Scanner(fileNumbers)) { // διαβάζουμε τους αριθμούς από το αρχείο, το try εξασφαλίσει ότι ο scanner θα κλείσει αυτο μετά το διάβασμα
            while (in.hasNextInt() && count < 49) { // hasNextInt= ελέγχει αν υπάρχουν άλλοι ακέραιοι στο αρχείο - θα τερματίσει όταν δεν υπάρχουν άλλοι ακέραιοι είτε όταν έχουμε διαβάσει 49 αριθμούς
                    nums = in.nextInt(); //διαβάζει τον επόμενο ακέραιο
                    if (nums >= 1 && nums <=49) { // ελέγχει αν ο αριθμός που διαβάστηκε είναι εντός του καθορισμένου εύρους (1 έως 49)
                        numsArray[count] = nums; // αν είναι έγκυρος, τον αποθηκεύουμε στον πίνακα στη θέση count
                        count++; // αυξάνουμε τη μεταβλητή count κατά 1, ώστε να δείχνει την επόμενη διαθέσιμη θέση στον πίνακα
                    }
            }


        }
        if (count < 7) {
            throw new InputMismatchException("Not enough valid numbers (at least 7 required).");
        }
        int [] finalArray = Arrays.copyOf(numsArray, count); // νέος πίνακας που περιέχει μόνο τους αριθμούς που διαβάστηκαν
        // η δεύτερη παράμετρος count καθορίζει πόσοι από τους αριθμούς του numsrArray θα συμπεριληφθούν στον νέο πίνακα. Έτσι, αποφεύγουμε να επιστρέψουμε τις κενές θέσεις του αρχικού πίνακα.
        Arrays.sort(finalArray); // ταξινόμηση του τελικού πίνακα
        return finalArray;

    }





    //φτιάχνει 6άδες από τον πίνακα
    public static void makeSixs (int[] finalArray) {
        int n = finalArray.length; // βρίσκει το μήκος του πίνακα αριθμών
        try (PrintWriter writer = new PrintWriter("output.txt")) { // Άνοιγμα αρχείου για εγγραφή
            for (int i = 0; i < n - 5; i++) {
                // Ξεκινάει τον πρώτο βρόχο, ο οποίος θα διατρέξει τους αριθμούς στον πίνακα.
                // Χρησιμοποιούμε n - 5 επειδή χρειαζόμαστε τουλάχιστον 6 αριθμούς για να
                // δημιουργήσουμε μια 6άδα (δηλαδή αν i είναι 0, οι επόμενοι δείκτες θα
                // είναι 1, 2, 3, 4 και 5)
                for (int j = i + 1; j < n - 4; j++) {
                    // ο δεύτερος βρόχος ξεκινά από τον δείκτη i + 1, διασφαλίζοντας ότι δεν
                    // θα επιλεγεί ο ίδιος αριθμός με τον πρώτο (πρώτος αριθμός της 6άδας).
                    // Χρησιμοποιούμε n - 4 για να έχουμε αρκετούς αριθμούς για τους επόμενους
                    // συνδυασμούς.
                    for (int k = j + 1; k < n - 3; k++) {
                        for (int l = k + 1; l < n - 2; l++) {
                            for (int m = l + 1; m < n - 1; m++) {
                                for (int o = m + 1; o < n; o++) {
                                    int[] combination = {finalArray[i], finalArray[j], finalArray[k], finalArray[l], finalArray[m], finalArray[o]};
                                    // δημιουργείται ένας πίνακας combination που περιέχει τους
                                    // έξι αριθμούς που έχουν επιλεγεί από τους προηγούμενους βρόχους.
                                    if (filter(combination)) {
                                        writer.println(Arrays.toString(combination));
                                        // καλείται η μέθοδος filterCombination για να ελέγξει αν η
                                        // 6άδα πληροί τα κριτήρια που ορίστηκαν. Αν ναι, η 6άδα
                                        // γράφεται στο αρχείο output.txt
                                        System.out.println("Valid combination: " + Arrays.toString(combination));
                                        // εκτύπβση και στην οθόνη

                                    }
                                }
                            }

                        }
                    }
                }
            }
        }  catch (FileNotFoundException e) {
            System.out.println("Unable to open output file: " + e.getMessage());
            //αν δεν μπορέσει να ανοίξει το αρχείο για εγγραφή, θα εμφανιστεί ένα μήνυμα σφάλματος.
    }

}



    //φιλτράρει
    public static boolean filter (int[] combination) {
        int evens = 0;
        int odds = 0;
        int contin = 0;
        int[] sameEnds = new int[10]; // πίνακας με λήγοντες
        int[] sameTens = new int[10]; // πίνακας δεκάδων

        for (int num : combination) { // άρτιοι - περιττοί
            if (num % 2 == 0) evens++;
            else odds++;

            sameEnds[num % 10]++; // έλεγχος λήγοντα
            sameTens[num / 10]++; // έλεγχος δεκάδας

        }

        // συνεχόμενοι αριθμοί
        for (int i = 0; i < combination.length - 1; i++) {
            if (combination[i + 1] - combination[i] == 1) {
                contin++;
            }
        }
        return evens <= 4 && odds <= 4 && contin <= 2 &&
                Arrays.stream(sameEnds).max().orElse(0) <= 3 &&
                Arrays.stream(sameTens).max().orElse(0) <= 3;
    }

}


