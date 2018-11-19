
public class GameOfDice {

	public static int game(String[] strings) {
		// TODO Auto-generated method stub
		if (strings.length % 2 != 0)
			return -1;

		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < strings.length; i++) {

			if (Integer.parseInt(strings[i]) > 6 || Integer.parseInt(strings[i]) < 1) {
				return -1;
			}

			if (i < strings.length / 2) {
				s1 += Integer.parseInt(strings[i]);
			} else {
				s2 += Integer.parseInt(strings[i]);
			}
		}
		
		if(s1 == s2){
			return 0; //draw
		}else if(s1 < s2){
			return 2; //(spieler 2
		}else{
			return 1; //spieler 1
		}
		
		
		
		//return s1 == s2 ? 0 : s1 < s2 ? 2 : 1;
	}

}
