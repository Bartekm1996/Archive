
package SoapConnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the SoapConnector package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOptions_QNAME = new QName("http://tttWebData/", "getOptions");
    private final static QName _Hello_QNAME = new QName("http://tttWebData/", "hello");
    private final static QName _Invite_QNAME = new QName("http://tttWebData/", "invite");
    private final static QName _ResetGame_QNAME = new QName("http://tttWebData/", "resetGame");
    private final static QName _BeginGame_QNAME = new QName("http://tttWebData/", "beginGame");
    private final static QName _LoginVerifyResponse_QNAME = new QName("http://tttWebData/", "loginVerifyResponse");
    private final static QName _BeginGameResponse_QNAME = new QName("http://tttWebData/", "beginGameResponse");
    private final static QName _Register_QNAME = new QName("http://tttWebData/", "register");
    private final static QName _GetTurnResponse_QNAME = new QName("http://tttWebData/", "getTurnResponse");
    private final static QName _GetStatsResponse_QNAME = new QName("http://tttWebData/", "getStatsResponse");
    private final static QName _DenyGameResponse_QNAME = new QName("http://tttWebData/", "denyGameResponse");
    private final static QName _GetTurn_QNAME = new QName("http://tttWebData/", "getTurn");
    private final static QName _HelloResponse_QNAME = new QName("http://tttWebData/", "helloResponse");
    private final static QName _RegisterResponse_QNAME = new QName("http://tttWebData/", "registerResponse");
    private final static QName _LoginVerify_QNAME = new QName("http://tttWebData/", "loginVerify");
    private final static QName _SetLoss_QNAME = new QName("http://tttWebData/", "setLoss");
    private final static QName _GetLeaderboard_QNAME = new QName("http://tttWebData/", "getLeaderboard");
    private final static QName _SetOnline_QNAME = new QName("http://tttWebData/", "setOnline");
    private final static QName _SetLossResponse_QNAME = new QName("http://tttWebData/", "setLossResponse");
    private final static QName _InviteResponse_QNAME = new QName("http://tttWebData/", "inviteResponse");
    private final static QName _ResetGameResponse_QNAME = new QName("http://tttWebData/", "resetGameResponse");
    private final static QName _SetWin_QNAME = new QName("http://tttWebData/", "setWin");
    private final static QName _SetWinResponse_QNAME = new QName("http://tttWebData/", "setWinResponse");
    private final static QName _GetOptionsResponse_QNAME = new QName("http://tttWebData/", "getOptionsResponse");
    private final static QName _Connect_QNAME = new QName("http://tttWebData/", "connect");
    private final static QName _SetMove_QNAME = new QName("http://tttWebData/", "setMove");
    private final static QName _SetOffline_QNAME = new QName("http://tttWebData/", "setOffline");
    private final static QName _DenyGame_QNAME = new QName("http://tttWebData/", "denyGame");
    private final static QName _GetJoiner_QNAME = new QName("http://tttWebData/", "getJoiner");
    private final static QName _GetLeaderboardResponse_QNAME = new QName("http://tttWebData/", "getLeaderboardResponse");
    private final static QName _SetDrawResponse_QNAME = new QName("http://tttWebData/", "setDrawResponse");
    private final static QName _GetJoinerResponse_QNAME = new QName("http://tttWebData/", "getJoinerResponse");
    private final static QName _SetDraw_QNAME = new QName("http://tttWebData/", "setDraw");
    private final static QName _GetStats_QNAME = new QName("http://tttWebData/", "getStats");
    private final static QName _CheckGameStartResponse_QNAME = new QName("http://tttWebData/", "checkGameStartResponse");
    private final static QName _CheckGameStart_QNAME = new QName("http://tttWebData/", "checkGameStart");
    private final static QName _SetMoveResponse_QNAME = new QName("http://tttWebData/", "setMoveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: SoapConnector
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStats }
     * 
     */
    public GetStats createGetStats() {
        return new GetStats();
    }

    /**
     * Create an instance of {@link CheckGameStartResponse }
     * 
     */
    public CheckGameStartResponse createCheckGameStartResponse() {
        return new CheckGameStartResponse();
    }

    /**
     * Create an instance of {@link CheckGameStart }
     * 
     */
    public CheckGameStart createCheckGameStart() {
        return new CheckGameStart();
    }

    /**
     * Create an instance of {@link SetMoveResponse }
     * 
     */
    public SetMoveResponse createSetMoveResponse() {
        return new SetMoveResponse();
    }

    /**
     * Create an instance of {@link GetJoiner }
     * 
     */
    public GetJoiner createGetJoiner() {
        return new GetJoiner();
    }

    /**
     * Create an instance of {@link GetLeaderboardResponse }
     * 
     */
    public GetLeaderboardResponse createGetLeaderboardResponse() {
        return new GetLeaderboardResponse();
    }

    /**
     * Create an instance of {@link SetDrawResponse }
     * 
     */
    public SetDrawResponse createSetDrawResponse() {
        return new SetDrawResponse();
    }

    /**
     * Create an instance of {@link GetJoinerResponse }
     * 
     */
    public GetJoinerResponse createGetJoinerResponse() {
        return new GetJoinerResponse();
    }

    /**
     * Create an instance of {@link SetDraw }
     * 
     */
    public SetDraw createSetDraw() {
        return new SetDraw();
    }

    /**
     * Create an instance of {@link SetOffline }
     * 
     */
    public SetOffline createSetOffline() {
        return new SetOffline();
    }

    /**
     * Create an instance of {@link DenyGame }
     * 
     */
    public DenyGame createDenyGame() {
        return new DenyGame();
    }

    /**
     * Create an instance of {@link SetWin }
     * 
     */
    public SetWin createSetWin() {
        return new SetWin();
    }

    /**
     * Create an instance of {@link SetWinResponse }
     * 
     */
    public SetWinResponse createSetWinResponse() {
        return new SetWinResponse();
    }

    /**
     * Create an instance of {@link GetOptionsResponse }
     * 
     */
    public GetOptionsResponse createGetOptionsResponse() {
        return new GetOptionsResponse();
    }

    /**
     * Create an instance of {@link Connect }
     * 
     */
    public Connect createConnect() {
        return new Connect();
    }

    /**
     * Create an instance of {@link SetMove }
     * 
     */
    public SetMove createSetMove() {
        return new SetMove();
    }

    /**
     * Create an instance of {@link GetLeaderboard }
     * 
     */
    public GetLeaderboard createGetLeaderboard() {
        return new GetLeaderboard();
    }

    /**
     * Create an instance of {@link SetOnline }
     * 
     */
    public SetOnline createSetOnline() {
        return new SetOnline();
    }

    /**
     * Create an instance of {@link SetLossResponse }
     * 
     */
    public SetLossResponse createSetLossResponse() {
        return new SetLossResponse();
    }

    /**
     * Create an instance of {@link InviteResponse }
     * 
     */
    public InviteResponse createInviteResponse() {
        return new InviteResponse();
    }

    /**
     * Create an instance of {@link ResetGameResponse }
     * 
     */
    public ResetGameResponse createResetGameResponse() {
        return new ResetGameResponse();
    }

    /**
     * Create an instance of {@link LoginVerify }
     * 
     */
    public LoginVerify createLoginVerify() {
        return new LoginVerify();
    }

    /**
     * Create an instance of {@link SetLoss }
     * 
     */
    public SetLoss createSetLoss() {
        return new SetLoss();
    }

    /**
     * Create an instance of {@link GetTurnResponse }
     * 
     */
    public GetTurnResponse createGetTurnResponse() {
        return new GetTurnResponse();
    }

    /**
     * Create an instance of {@link GetStatsResponse }
     * 
     */
    public GetStatsResponse createGetStatsResponse() {
        return new GetStatsResponse();
    }

    /**
     * Create an instance of {@link DenyGameResponse }
     * 
     */
    public DenyGameResponse createDenyGameResponse() {
        return new DenyGameResponse();
    }

    /**
     * Create an instance of {@link GetTurn }
     * 
     */
    public GetTurn createGetTurn() {
        return new GetTurn();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link GetOptions }
     * 
     */
    public GetOptions createGetOptions() {
        return new GetOptions();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link Invite }
     * 
     */
    public Invite createInvite() {
        return new Invite();
    }

    /**
     * Create an instance of {@link ResetGame }
     * 
     */
    public ResetGame createResetGame() {
        return new ResetGame();
    }

    /**
     * Create an instance of {@link BeginGame }
     * 
     */
    public BeginGame createBeginGame() {
        return new BeginGame();
    }

    /**
     * Create an instance of {@link LoginVerifyResponse }
     * 
     */
    public LoginVerifyResponse createLoginVerifyResponse() {
        return new LoginVerifyResponse();
    }

    /**
     * Create an instance of {@link BeginGameResponse }
     * 
     */
    public BeginGameResponse createBeginGameResponse() {
        return new BeginGameResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOptions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getOptions")
    public JAXBElement<GetOptions> createGetOptions(GetOptions value) {
        return new JAXBElement<GetOptions>(_GetOptions_QNAME, GetOptions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Invite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "invite")
    public JAXBElement<Invite> createInvite(Invite value) {
        return new JAXBElement<Invite>(_Invite_QNAME, Invite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "resetGame")
    public JAXBElement<ResetGame> createResetGame(ResetGame value) {
        return new JAXBElement<ResetGame>(_ResetGame_QNAME, ResetGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeginGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "beginGame")
    public JAXBElement<BeginGame> createBeginGame(BeginGame value) {
        return new JAXBElement<BeginGame>(_BeginGame_QNAME, BeginGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginVerifyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "loginVerifyResponse")
    public JAXBElement<LoginVerifyResponse> createLoginVerifyResponse(LoginVerifyResponse value) {
        return new JAXBElement<LoginVerifyResponse>(_LoginVerifyResponse_QNAME, LoginVerifyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeginGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "beginGameResponse")
    public JAXBElement<BeginGameResponse> createBeginGameResponse(BeginGameResponse value) {
        return new JAXBElement<BeginGameResponse>(_BeginGameResponse_QNAME, BeginGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTurnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getTurnResponse")
    public JAXBElement<GetTurnResponse> createGetTurnResponse(GetTurnResponse value) {
        return new JAXBElement<GetTurnResponse>(_GetTurnResponse_QNAME, GetTurnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getStatsResponse")
    public JAXBElement<GetStatsResponse> createGetStatsResponse(GetStatsResponse value) {
        return new JAXBElement<GetStatsResponse>(_GetStatsResponse_QNAME, GetStatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DenyGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "denyGameResponse")
    public JAXBElement<DenyGameResponse> createDenyGameResponse(DenyGameResponse value) {
        return new JAXBElement<DenyGameResponse>(_DenyGameResponse_QNAME, DenyGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTurn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getTurn")
    public JAXBElement<GetTurn> createGetTurn(GetTurn value) {
        return new JAXBElement<GetTurn>(_GetTurn_QNAME, GetTurn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginVerify }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "loginVerify")
    public JAXBElement<LoginVerify> createLoginVerify(LoginVerify value) {
        return new JAXBElement<LoginVerify>(_LoginVerify_QNAME, LoginVerify.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetLoss }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setLoss")
    public JAXBElement<SetLoss> createSetLoss(SetLoss value) {
        return new JAXBElement<SetLoss>(_SetLoss_QNAME, SetLoss.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLeaderboard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getLeaderboard")
    public JAXBElement<GetLeaderboard> createGetLeaderboard(GetLeaderboard value) {
        return new JAXBElement<GetLeaderboard>(_GetLeaderboard_QNAME, GetLeaderboard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetOnline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setOnline")
    public JAXBElement<SetOnline> createSetOnline(SetOnline value) {
        return new JAXBElement<SetOnline>(_SetOnline_QNAME, SetOnline.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetLossResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setLossResponse")
    public JAXBElement<SetLossResponse> createSetLossResponse(SetLossResponse value) {
        return new JAXBElement<SetLossResponse>(_SetLossResponse_QNAME, SetLossResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InviteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "inviteResponse")
    public JAXBElement<InviteResponse> createInviteResponse(InviteResponse value) {
        return new JAXBElement<InviteResponse>(_InviteResponse_QNAME, InviteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "resetGameResponse")
    public JAXBElement<ResetGameResponse> createResetGameResponse(ResetGameResponse value) {
        return new JAXBElement<ResetGameResponse>(_ResetGameResponse_QNAME, ResetGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetWin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setWin")
    public JAXBElement<SetWin> createSetWin(SetWin value) {
        return new JAXBElement<SetWin>(_SetWin_QNAME, SetWin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetWinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setWinResponse")
    public JAXBElement<SetWinResponse> createSetWinResponse(SetWinResponse value) {
        return new JAXBElement<SetWinResponse>(_SetWinResponse_QNAME, SetWinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOptionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getOptionsResponse")
    public JAXBElement<GetOptionsResponse> createGetOptionsResponse(GetOptionsResponse value) {
        return new JAXBElement<GetOptionsResponse>(_GetOptionsResponse_QNAME, GetOptionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Connect }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "connect")
    public JAXBElement<Connect> createConnect(Connect value) {
        return new JAXBElement<Connect>(_Connect_QNAME, Connect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setMove")
    public JAXBElement<SetMove> createSetMove(SetMove value) {
        return new JAXBElement<SetMove>(_SetMove_QNAME, SetMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetOffline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setOffline")
    public JAXBElement<SetOffline> createSetOffline(SetOffline value) {
        return new JAXBElement<SetOffline>(_SetOffline_QNAME, SetOffline.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DenyGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "denyGame")
    public JAXBElement<DenyGame> createDenyGame(DenyGame value) {
        return new JAXBElement<DenyGame>(_DenyGame_QNAME, DenyGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJoiner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getJoiner")
    public JAXBElement<GetJoiner> createGetJoiner(GetJoiner value) {
        return new JAXBElement<GetJoiner>(_GetJoiner_QNAME, GetJoiner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLeaderboardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getLeaderboardResponse")
    public JAXBElement<GetLeaderboardResponse> createGetLeaderboardResponse(GetLeaderboardResponse value) {
        return new JAXBElement<GetLeaderboardResponse>(_GetLeaderboardResponse_QNAME, GetLeaderboardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetDrawResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setDrawResponse")
    public JAXBElement<SetDrawResponse> createSetDrawResponse(SetDrawResponse value) {
        return new JAXBElement<SetDrawResponse>(_SetDrawResponse_QNAME, SetDrawResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJoinerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getJoinerResponse")
    public JAXBElement<GetJoinerResponse> createGetJoinerResponse(GetJoinerResponse value) {
        return new JAXBElement<GetJoinerResponse>(_GetJoinerResponse_QNAME, GetJoinerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetDraw }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setDraw")
    public JAXBElement<SetDraw> createSetDraw(SetDraw value) {
        return new JAXBElement<SetDraw>(_SetDraw_QNAME, SetDraw.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "getStats")
    public JAXBElement<GetStats> createGetStats(GetStats value) {
        return new JAXBElement<GetStats>(_GetStats_QNAME, GetStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckGameStartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "checkGameStartResponse")
    public JAXBElement<CheckGameStartResponse> createCheckGameStartResponse(CheckGameStartResponse value) {
        return new JAXBElement<CheckGameStartResponse>(_CheckGameStartResponse_QNAME, CheckGameStartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckGameStart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "checkGameStart")
    public JAXBElement<CheckGameStart> createCheckGameStart(CheckGameStart value) {
        return new JAXBElement<CheckGameStart>(_CheckGameStart_QNAME, CheckGameStart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMoveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tttWebData/", name = "setMoveResponse")
    public JAXBElement<SetMoveResponse> createSetMoveResponse(SetMoveResponse value) {
        return new JAXBElement<SetMoveResponse>(_SetMoveResponse_QNAME, SetMoveResponse.class, null, value);
    }

}
