package Model.PersistentStorage;

import Model.Boat;
import Model.Member;
import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableMember;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Persistent storage using txt.
 */
public class PersistentStorageUsingTxt implements PersistentStorage {
    private String pathToTxtFiles = getPathToTxtFilesFolder();

    /**
     * Instantiates a new Persistent storage using txt.
     */
    public PersistentStorageUsingTxt() {
        createNecessaryFiles();
    }

    /**
     * Saves each member and its boats in a txt file. (only saves name, personal number, id and each boat with length and type)
     * @param members the v member
     */
    public void saveMembers(List<Member> members) {
        String membersData = "";
        for (ViewableMember member : members) {
            membersData += memberToString(member);
        }
        File memberCountTxt = new File(pathToTxtFiles + "members");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(memberCountTxt));
            writer.write(membersData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Takes a vMember which is then used to return a string which the persistent storage later can read.
     * @param vMember
     * @return A string used to store the member in a txt file
     */
    private String memberToString(ViewableMember vMember) {
        String memberData = vMember.getName() + ";" + vMember.getPersonalNumber() + ";" + vMember.getId() + ";" + vMember.getNumberOfBoats() + ";\n";
        for (ViewableBoat boat : vMember.getViewableBoats()) {
            memberData += boatToString(boat);
        }
        return memberData;
    }

    /**
     * Takes a vBoat which is then used to return a string which the persistent storage later can read.
     * @param vBoat
     * @return A string used to store the boat in a txt file
     */
    private String boatToString(ViewableBoat vBoat) {
        return vBoat.getLength() + ";" + vBoat.getType() + ";\n";
    }


    /**
     * Reads the txt file members, creates a member object for each member in the file and returns the list.
     * @return The list of members that is returned.
     */
    public List<Member> getMembers() {
        final List<Member> membersList = new ArrayList<>();
        FileReader fileToRead = null;

        try {
            fileToRead = new FileReader(pathToTxtFiles + "members");
            BufferedReader bf = new BufferedReader(fileToRead);
            String memberData = bf.readLine();
            while (memberData != null) {
                membersList.add(readMemberFromFile(memberData, bf));
                memberData = bf.readLine();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return membersList;
    }

    private Member readMemberFromFile(String memberData, BufferedReader bf) {
        String[] splitMemberData = memberData.split(";");

        String name = splitMemberData[0];
        String personalNumber = splitMemberData[1];
        int id = Integer.parseInt(splitMemberData[2]);
        int numberOfBoats = Integer.parseInt(splitMemberData[3]);

        Member member = new Member(name, personalNumber, id);
        readMemberBoatsFromFile(member, numberOfBoats, bf);

        return member;
    }

    /**
     * Reads the next line and creates a new boat with the information. Then adds the boat to the member.
     * @param member
     * @param numberOfBoats tells the function how many lines to read
     * @param bf a buffer reader which next line is a boat.
     */
    private void readMemberBoatsFromFile(Member member, int numberOfBoats, BufferedReader bf) {
        try {
            for (int i = 0; i < numberOfBoats; i++) {
                String boatData = bf.readLine();
                String[] splitBoatData = boatData.split(";");

                int length = Integer.parseInt(splitBoatData[0]);
                Boat.Type type = Boat.Type.valueOf(splitBoatData[1]);

                member.addBoat(new Boat(type,length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Updates the current number of members that have been created so far.
     *
     * @param numberOfMembers
     */
    public void updateMemberCount (String numberOfMembers) {
        try {
            FileWriter fw = new FileWriter(pathToTxtFiles + "memberCreationCount");
            PrintWriter pw = new PrintWriter(fw);

            pw.printf("%s" + "%n", numberOfMembers);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @return the number of members
     */
    public int getMemberCount() {
        FileReader fileToRead = null;
        try {
            fileToRead = new FileReader(pathToTxtFiles + "memberCreationCount");
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(fileToRead);

        int memberCount = 0;
        try {
            memberCount = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return memberCount;
    }

    private void createNecessaryFiles() {
        createTxtFilesFolder();
        createMemberCreationCountFile();
        createMembersFile();
    }

    /**
     * Creates the folder which the members file and membersCreationCount is stored.
     */
    private void createTxtFilesFolder() {
        File txtFileHolder = new File(pathToTxtFiles);
        if (!txtFileHolder.exists()) {
            txtFileHolder.mkdir();
        }
    }

    /**
     * Creates the file which stores how many members have been created.
     */
    private void createMemberCreationCountFile() {
        File memberCountTxt = new File(pathToTxtFiles + "memberCreationCount");

        if (!memberCountTxt.exists()) {
            try {
                memberCountTxt.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(memberCountTxt));
                writer.write("1");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the file which stores all the members information including boats.
     */
    private void createMembersFile() {
        File memberCountTxt = new File(pathToTxtFiles + "members");

        if (!memberCountTxt.exists()) {
            try {
                memberCountTxt.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * returns The full path to the txtFiles folder which stores the members file and membersCreationCount
     * @return
     */
    private String getPathToTxtFilesFolder() {
        String standardPath = System.getProperty("user.dir");
        String relativePath = "/txtFiles/"; //unsure how this will work on other operating systems than windows.
        String completePath = standardPath + relativePath;
        return completePath;
    }
}
