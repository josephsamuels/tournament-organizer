package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public class TournamentStage<E extends AbstractParticipant> {
    private final ArrayList<E> stageParticipants;
    
    private final int MIN_PAIRING, MAX_PAIRING;
    
    private final TournamentStageStyle STYLE;
    private final TournamentStageFormat FORMAT;
    
    public TournamentStage(ArrayList<E> stageParticipants, TournamentStageStyle style) {
        this(stageParticipants, style, TournamentStageFormat.HEADSUP, 1, 2);
    }
    
    public TournamentStage(
            ArrayList<E> stageParticipants,
            TournamentStageStyle style,
            TournamentStageFormat format,
            int minPairingCount, 
            int maxPairingCount) {
        this.stageParticipants = stageParticipants;
        this.MIN_PAIRING = minPairingCount;
        this.MAX_PAIRING = maxPairingCount;
        this.FORMAT = format;
        this.STYLE = style;
    }
}
