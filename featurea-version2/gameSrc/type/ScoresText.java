package type;

import data.textcolors;

public class ScoresText extends featurea.Text{
	
	public ScoresText(int totalScore) {
		super("", "main", 16, textcolors.blue, "bold");		
		String stringTotalScore = "" + totalScore;
		int length = stringTotalScore.length();
        for (int i = 0; i < 7 - length; i++) {
            stringTotalScore = "0" + stringTotalScore;
        }
        this.string = stringTotalScore;
	}
	
	public ScoresText(String totalScore) {
		super(totalScore, "main", 16, textcolors.blue, "bold");
	}

}