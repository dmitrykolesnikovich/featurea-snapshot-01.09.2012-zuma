package type;

import areas.Burst;
import areas.FlyNut;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import data.musics;
import data.sounds;
import data.sprites;
import data.stacks;
import areas.Background;
import areas.Chain;
import areas.Dot;
import areas.Path;
import areas.Pile;
import areas.ScoreupText;
import areas.Scrat;
import areas.StartForNuts;
import data.parameters;
import featurea.Music;
import featurea.Play;
import featurea.Sound;
import featurea.Sprite1;
import featurea.Vector3;
import game.settings;
import data.values;
import data.screen.Hud;
import featurea.game;

public class Level extends Play {

	public Sprite1[] backgroundSprite;
    public long changemodetime = values.changemodetime;
    public List<Pile> piles = new ArrayList<Pile>();
    public Path[] paths;
    public Background background;
    
    public Level(int colorsCount, int levelIndex, Path[] paths, Background background, Vector3 scratPoint) {   
    	super(stacks.stack_version2);    	
    	parameters.levelUpStarts = false;
        values.COINS_IN_LEVEL_TO_APPEAR = 5;
        values.isCoinInLevel = false;
        values.timeCoinIsNotInLevel = 0;
        piles.clear();
        this.background = background;
        // Reset all static values for features.
        data.values.moveAlready = false;
        data.values.firstNutHasBeenBursted = false;
        data.values.levelLifeTime = 0;
        parameters.shouldChangeState = false;    
        this.paths = paths;

        /** Areas */
        this.add(background);

        for (int i = 0; i < paths.length; i++) {
            Path path = paths[i];

            StartForNuts ba = new StartForNuts(path.startPoint.copy());
            paths[i].startfornuts = ba;
            this.add(ba);

            Chain chain = new Chain(path);
            Nut firstNut = new Nut((int) (Math.random() * colorsCount), path.startPoint.copy(), path.trajectory.copy(), null, null, null);

            chain.nuts.add(firstNut);

            paths[i].chains.add(chain);
            this.add(firstNut);
            this.add(chain);
            this.add(paths[i]);
        }

        // Hud        
        parameters.colorsCount = colorsCount;
        parameters.levelScore = 0;
        // Scrat        
        Scrat scrat = new Scrat(scratPoint);
        this.add(scrat);
        Vector3 pilePoint = scratPoint.copy();
        pilePoint.minus(new Vector3(0, 0, 1));
        Pile pile = new Pile(pilePoint);
        this.add(pile);
        scrat.pile = pile;
        piles.add(pile);
    }

    
    public static Sprite1[][] spriteResources = new Sprite1[][]{
		sprites.bornarea_debug, sprites.nut_red, sprites.nut_blue, sprites.nut_yellow, sprites.nut_green, sprites.nut_purple,
		sprites.nut_red_burn, sprites.nut_blue_burn, sprites.nut_yellow_burn, sprites.nut_green_burn, 
		sprites.nut_purple_burn, sprites.nut_white, sprites.nut_pink, sprites.coin, sprites.scrat, sprites.pile, sprites.burst,
		sprites.burn, sprites.fire, sprites.dot, sprites.life, sprites.tapfortap_ad_background, sprites.tapfortap_button_close_ad,
		sprites.tapfortap_button_close_ad_down, sprites.tapfortap_logo, 
	};
    public static Sound[] soundResources = new Sound[]{
		sounds.scrat_throw_nut, sounds.flynut_contacts_nut, sounds.score_up, sounds.nut_contacts_coin, 
	};
    public static Music[] musicResources = new Music[]{
    	musics.loop, musics.nuts_are_moving
    };
       

    public List<Nut> nutsInChain() {
        List<Nut> nuts = new ArrayList<Nut>();
        for (Path path : paths) {
            for (Chain chain : path.chains) {
                nuts.addAll(chain.nuts);
            }
        }
        return nuts;
    }

    public int[] existingcolors() {
        Set<Integer> set = new HashSet<Integer>();
        for (Nut nut : nutsInChain()) {
            if (nut != null) {
                set.add(nut.color);
            }
        }
        int[] array = new int[set.size()];
        int i = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }
    
    public static void removeNutFromChainAndGameplay(Nut nut){
        int indexinchain = nut.indexinchain();
        Chain chain = nut.chain();
        if (game.play.children.contains(nut)){
            final Path path = chain.path;
            chain.nuts.remove(nut);
            game.play.remove(nut);        
            Chain headChain = new Chain(path);
            Chain tailChain = new Chain(path);
            for (int i = 0; i <= indexinchain - 1; i++) {
                tailChain.nuts.add(chain.nut(i));
            }
            for (int i = indexinchain; i < chain.size(); i++) {
                headChain.nuts.add(chain.nut(i));
            }
            int chainIndex = path.chains.indexOf(chain);
            game.play.remove(chain);
            path.chains.remove(chain);

            // Trick: order of adding is very important! -------------
            game.play.add(headChain);
            path.add(chainIndex, headChain);
            game.play.add(tailChain);
            path.add(chainIndex, tailChain);
            // -------------------------------------------------------

            // State
            chain.state(values.state_stop);
            headChain.stateNotChangedYet = tailChain.stateNotChangedYet = chain.stateNotChangedYet;
 
            // Clean empty chains.
            if (headChain.isEmpty()) {
            	game.play.remove(headChain);
                headChain.path.chains.remove(headChain);
            }
            if (tailChain.isEmpty()) {
            	game.play.remove(tailChain);
                tailChain.path.chains.remove(tailChain);
            }
        }
    }

    private List<Dot> dots = new ArrayList<Dot>();
	public void addDot(Dot dot) {
		game.play.add(dot);
		dots.add(dot);
		
	}
	public boolean hasDots(){
		return dots.size() > 0;
	}

	public void clearDots() {
		for (Dot dot : dots){
			game.play.remove(dot);
		}
		dots.clear();		
	}

    private List<FlyNut> flyNuts = new ArrayList<FlyNut>();
    public void addFlyNut(FlyNut flyNut) {
    	game.play.add(flyNut);
    	flyNuts.add(flyNut);
    }

    public void removeFlyNut(FlyNut flyNut) {
    	game.play.remove(flyNut);
        flyNuts.remove(flyNut);
    }
    
    public FlyNut doesNutFly(Nut nut){
        for (FlyNut flyNut : flyNuts){
            if (flyNut.nut == nut){
                return flyNut;
            }
        }
        return null;
    }

    private List<Burst> bursts = new ArrayList<Burst>();
    public void addBurst(Burst burst) {
        add(burst);
        bursts.add(burst);
    }

    public void removeBurst(Burst burst) {
        remove(burst);
        bursts.remove(burst);
    }
    public boolean hasBursts(){
        return !bursts.isEmpty();
    }
    
    // Instead of 'Burst' we have 'ScoreupText'
    private List<ScoreupText> scoreupTexts = new ArrayList<ScoreupText>();
    public void addScoreupText(ScoreupText scoreupText) {
        this.add(scoreupText);
        scoreupTexts.add(scoreupText);
    }

    public void removeScoreupText(ScoreupText scoreupText) {
    	this.remove(scoreupText);		
        scoreupTexts.remove(scoreupText);
    }
    public boolean hasScoreupText(){
        return !scoreupTexts.isEmpty();
    }
    
    
    
}