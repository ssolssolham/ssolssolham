package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.MinusAccount;

/**
 * AccountPanel 클래스의 목적 1. MainFrame에 담기 위한 Panel 2. 익명 내부클래스를 통한
 * ActionListener,ItemListener 구현
 * 
 * AccountPanel 클래스의 구조 1. 인스턴스 변수 :
 * Label,Choice,TextField,Button,GridBagLayout,GridBagConstraints 등 Panel 내 요소와
 * Panel 외부의 MainFrame
 * 
 * 2. 생성자 : mainFrame을 받아서 집합관계 구성, Panel 내 인스턴스 변수 초기화, 내부클래스를 통한
 * ActionListener와 ItemListener 생성
 * 
 * 3. 인스턴스 메소드 :
 * add(),setContents(),printlead(),printend(),decimalVerify(TextField tf),moneyVerify(TextField tf)
 * selectType(),getAccount(),removeAccount(),addAccount(),listAll(),searchAccount()
 * 
 * 	- add : gridBagConstraints를 이용해 gridBagLayout 바탕의 component에 넣기 
 * 	- setContents : Panel의 화면구성 
 *  - printlead : AMS에서 계좌조회,전체조회 등 출력시 화면에 첫 출력되는 메소드
 *  - printend :AMS에서 계좌조회,전체조회 등 출력시 화면의 마지막에 출력되는 메소드 
 *  - decimalVerify : 아스키코드를 이용해 TextField 내 내용이 숫자인지 판단하는 메소드 
 *  - moneyVerify : TextField의 내용이 양수,음수인지 판단하는 메소드
 *  - selectType(),getAccount(),removeAccount(),addAccount(),listAll(),searchAccount() : 이벤트에 따른 메소드
 * 
 * @author 박호준
 */
public class AccountPanel extends Panel {

	MainFrame mainFrame;

	Label accountType, accountNum, accountOwner, accountPw, loanMoney, depositMoney, accountList, unitMoney, allet;
	Choice accountTypeC;
	TextField accountNumTF, accountOwnerTF, accountPwTF, loanMoneyTF, depositMoneyTF;
	TextArea accountListTA;
	Button accountCheckB, accountDeleteB, accountSearchB, accountAddB, accountSearchAllB;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	/**
	 * AccountPanel 생성자
	 * 
	 * @param mainFrame : 주소값 참조할 MainFrame
	 */
	public AccountPanel(MainFrame mainFrame) {
		// 집합관계의 MainFrame, Panel을 넣기 위해 주소값 참조
		this.mainFrame = mainFrame;

		// 생성자를 이용한 인스턴스 변수 초기화
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		accountType = new Label("계좌종류");
		accountNum = new Label("계좌번호");
		accountOwner = new Label("예금주명");
		accountPw = new Label("비밀번호");
		loanMoney = new Label("대출금액");
		depositMoney = new Label("입금금액");
		accountList = new Label("계좌목록");
		unitMoney = new Label("(단위 : 원)");
		allet = new Label();

		accountTypeC = new Choice();
		accountTypeC.add("전체");
		accountTypeC.add("입출금 계좌");
		accountTypeC.add("마이너스 계좌");

		accountNumTF = new TextField();
		accountOwnerTF = new TextField();
		accountPwTF = new TextField();
		loanMoneyTF = new TextField();
		depositMoneyTF = new TextField();

		accountListTA = new TextArea();

		accountCheckB = new Button("조회");
		accountDeleteB = new Button("삭제");
		accountSearchB = new Button("검색");
		accountAddB = new Button("신규등록");
		accountSearchAllB = new Button("전체조회");

		// 화면 구성 및 TextArea 비활성화
		setContents();
		accountListTA.setEnabled(false);
		eventRegist();
		
		// 비밀번호 표기 암호화
		accountPwTF.setEchoChar('*');
	}

	/**
	 * gridBagConstraints를 이용해 gridBagLayout 바탕의 component에 넣기
	 * 
	 * @param component  : 넣으려는 컴포넌트 ex) Panel,Frame
	 * @param gridx      : x좌표
	 * @param gridy      : y좌표
	 * @param gridwidth  : x의 크기
	 * @param gridheight : y의 크기
	 * @param weightx    : x 가중치
	 * @param weighty    : y 가중치
	 */
	public void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(4, 1, 4, 1);

		gridBagLayout.setConstraints(component, gridBagConstraints);
		add(component);

	}

	/**
	 * Panel의 화면구성 - gridBagLayout 바탕에 인스턴스 변수 넣기 - TextArea 비활성화
	 */
	public void setContents() {
		setLayout(gridBagLayout);
		add(accountType, 0, 0, 1, 1, 0, 0);
		add(accountTypeC, 1, 0, 1, 1, 1, 0);

		add(accountNum, 0, 1, 1, 1, 0, 0);
		add(accountNumTF, 1, 1, 2, 1, 0, 0);
		add(accountCheckB, 3, 1, 1, 1, 0, 0);
		add(accountDeleteB, 4, 1, 1, 1, 0, 0);

		add(accountOwner, 0, 2, 1, 1, 0, 0);
		add(accountOwnerTF, 1, 2, 2, 1, 0, 0);
		add(accountSearchB, 3, 2, 2, 1, 0, 0);

		add(accountPw, 0, 3, 1, 1, 0, 0);
		add(accountPwTF, 1, 3, 2, 1, 0, 0);
		add(depositMoney, 3, 3, 1, 1, 0, 0);
		add(depositMoneyTF, 4, 3, 2, 1, 0, 0);

		add(loanMoney, 0, 4, 1, 1, 0, 0);
		add(loanMoneyTF, 1, 4, 2, 1, 0, 0);
		add(accountAddB, 3, 4, 1, 1, 0, 0);
		add(accountSearchAllB, 4, 4, 1, 1, 0, 0);

		add(accountList, 0, 5, 1, 1, 0, 0);
		add(allet, 1, 5, 3, 1, 0, 0);
		add(unitMoney, 4, 5, 1, 1, 0, 0);

		add(accountListTA, 0, 6, 5, 1, 0, 0);

		loanMoneyTF.setEnabled(false);
	}

	/**
	 * 이벤트 등록 메소드 
	 */
	public void eventRegist() {
		// 계좌번호를 통한 계좌조회시 이벤트 활성화
		accountCheckB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getAccount();
			}
		});

		// 계좌번호를 통한 계좌삭제 시 이벤트 활성화
		accountDeleteB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeAccount();
			}

		});

		// 소유주를 통해 계좌검색 시 이벤트 활성화
		accountSearchB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchAccount();
			}

		});

		// 계좌추가 이벤트 활성화
		accountAddB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addAccount();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// 계좌 전체 조회 이벤트
		accountSearchAllB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listAll();
			}
		});

		// Choice 객체 선택 이벤트
		accountTypeC.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				selectType(e);
			}

		});
	}
	/**
	 * AMS에서 계좌조회,전체조회 등 출력시 화면에 첫 출력되는 메소드
	 */
	public void printlead() {
		accountListTA.append(
				"--------------------------------------------------------------------------------------------\n");
		accountListTA
				.append("  계좌종류         계좌번호                    예금주명                현재잔액                  대출금액 \n");
		accountListTA.append(
				"============================================================================================\n");
	}

	/**
	 * AMS에서 계좌조회,전체조회 등 출력시 화면의 마지막에 출력되는 메소드
	 */
	public void printend() {
		accountListTA.append(
				"--------------------------------------------------------------------------------------------\n");
	}

	/**
	 * 아스키코드를 이용해 TextField 내 내용이 숫자인지 판단하는 메소드 - 판단하여 숫자가 아니면 allet이라는 Label에 빨간글씨로
	 * 출력
	 * 
	 * @param tf : TextField 내 내용
	 * 
	 * @return TextField 내 내용이 숫자인지 판단하여 숫자면 true, 그 외에는 false;
	 */
	public boolean decimalVerify(TextField tf) {
		for (int i = 0; i < tf.getText().length(); i++) {
			if (tf.getText().charAt(i) >= 48 && tf.getText().charAt(i) <= 57) {
			} else {
				allet.setText("비밀번호는 숫자로만 이루어져야 합니다.");
				allet.setForeground(Color.RED);
				return false;
			}
		}
		return true;
	}

	/**
	 * TextField의 내용이 양수,음수인지 판단하는 메소드 - 판단하여 숫자가 아니면 allet이라는 Label에 빨간글씨로 출력
	 * 
	 * @param tf : TextField 내 내용
	 * 
	 * @return TextField 내 내용이 양수인지 판단하여 숫자면 true, 그 외에는 false;
	 */
	public boolean moneyVerify(TextField tf) {

		if (Integer.parseInt(tf.getText()) >= 0) {

		} else {
			allet.setText("입금 혹은 대출금액은 양수입니다.");
			allet.setForeground(Color.RED);
			return false;
		}

		return true;
	}

	/**
	 * ItemEvent의 선택에 따른 대출금액 활성화, 비활성화
	 * 
	 * @param e : Item을 선택했을 경우의 ItemEvent 객체
	 */
	public void selectType(ItemEvent e) {
		if (e.getSource() == accountTypeC) {
			if (accountTypeC.getSelectedIndex() == 2) {
				// 마이너스 계좌 선택시 대출금액 활성화
				loanMoneyTF.setEnabled(true);
			} else if (accountTypeC.getSelectedIndex() == 1 || accountTypeC.getSelectedIndex() == 0) {
				// 전체, 입출금 계좌 선택시 대출금액 비활성화
				loanMoneyTF.setEnabled(false);
			}
		}

	}

	/**
	 * Actionlistener에 따라 계좌번호로 계좌조회를 하는 메소드
	 */
	public void getAccount() {
		try {
			
			// 유효성 검사
			if (accountNumTF.getText() == null || accountNumTF.getText().trim().length() == 0) {
				allet.setText("빈칸으로는 찾을 수 없습니다.");
				allet.setForeground(Color.RED);
				return;
			}
			
			Account account = mainFrame.accountDao.get(accountNumTF.getText());
			if (account != null) {
				// 계좌가 있을 시 출력
				accountListTA.setText("");
				printlead();
				accountListTA.append(account.toString());
				accountListTA.append("\n");
				printend();
			} else {
				// 계좌가 없을 시 allet이라는 Label에 출력
				allet.setText("찾는 계좌가 없습니다.");
				allet.setForeground(Color.RED);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Actionlistener에 따라 계좌소유주를 통해 계좌조회를 하는 메소드
	 */
	public void searchAccount() {
		try {
			// 유효성 검사
			if (accountOwnerTF.getText() == null || accountOwnerTF.getText().trim().length() == 0) {
				allet.setText("빈칸으로는 검색할 수 없습니다.");
				allet.setForeground(Color.RED);
				return;
			}
			
			List<Account> accounts = mainFrame.accountDao.search(accountOwnerTF.getText());
			if (accounts != null && !(accounts.isEmpty())) {
				// 계좌 목록이 있을시
				accountListTA.setText("");
				printlead();
				for (Account account : accounts) {
					accountListTA.append(account.toString() + "\n");
				}
				printend();
				allet.setText("검색 완료");
				allet.setForeground(Color.GREEN);
			} else if (accounts.isEmpty()) {
				// 예외처리 시 실행되는 구간
				accountListTA.setText("");
				allet.setText("검색하고자 하는 계좌가 없습니다.");
				allet.setForeground(Color.RED);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * ActionListener에 따라 전체 조회하는 메소드
	 */
	public void listAll() {

		try {
			List<Account> accounts = mainFrame.accountDao.listAll();
			if (accounts != null) {
				// 계좌목록이 있을시
				accountListTA.setText("");
				printlead();
				for (Account account : accounts) {
					accountListTA.append(account.toString() + "\n");
				}
				allet.setText("전체 조회 완료");
				allet.setForeground(Color.GREEN);
				printend();
			} else {
				// 전체 조회한 결과가 아예 없을 경우
				allet.setText("계좌가 없습니다.");
				allet.setForeground(Color.RED);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * ActionListener에 따라 계좌를 추가하는 메소드
	 * 
	 * @throws IOException
	 */
	private void addAccount() throws IOException {

		// 유효성 검사
		if (accountNumTF.getText().trim().length() == 0 || accountOwnerTF.getText().trim().length() == 0
				|| accountPwTF.getText().trim().length() == 0) {
			// 비어있을 경우 error 발생, Label인 allet에 출력
			allet.setText("빈칸으로는 추가할 수 없습니다.");
			allet.setForeground(Color.RED);
			return;
		}

		// 패스워드가 숫자인지 판단
		if (decimalVerify(accountPwTF)) {} else {return;}

		// 계좌 중복 여부 확인
		if (mainFrame.accountDao.get(accountNumTF.getText()) != null) {
			// 일치할 경우 error 발생, Label인 allet에 출력
			allet.setText("이미 등록된 계좌입니다.");
			allet.setForeground(Color.RED);
			return;
		}
		
		// 계좌 선택 여부 확인
		if(accountTypeC.getSelectedIndex() == 0) {
			allet.setText("계좌의 종류를 선택하세요");
			allet.setForeground(Color.RED);
			return;
		}
		
		// 계좌번호 입력 전 유효성검사
		if(accountNumTF.getText().length() != 14 || accountOwnerTF.getText().length() < 3 || accountPwTF.getText().length() != 4) {
			allet.setText("계좌번호(하이픈 2개 포함) 14자리,성명 3자리 이상,비밀번호 숫자 4자리로 입력해주세요");
			allet.setForeground(Color.RED);
			return;
		}
		
		if (accountTypeC.getSelectedIndex() == 1) {
			// 입출금계좌 등록시
			// 입금금액이 빈칸인지 검사
			if (depositMoneyTF.getText().trim().length() == 0) {
				allet.setText("금액을 채워주세요");
				allet.setForeground(Color.RED);
				return;
			}
			// 입금금액이 양수인지 검사
			if (moneyVerify(depositMoneyTF)) {} else {return;}
			
			
			try {
				// 계좌 생성
				mainFrame.accountDao.create(new Account(accountNumTF.getText(), accountOwnerTF.getText(),
						Integer.parseInt(accountPwTF.getText()), Long.parseLong(depositMoneyTF.getText())));
				// 정상적인 등록완료시 출력
				allet.setText("등록 완료");
				allet.setForeground(Color.GREEN);	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (accountTypeC.getSelectedIndex() == 2) {
			// 입금금액,대출금액이 빈칸일 경우 에러 발생
			if (depositMoneyTF.getText().trim().length() == 0 || loanMoneyTF.getText().trim().length() == 0) {
				allet.setText("금액을 채워주세요");
				allet.setForeground(Color.RED);
				return;
			}
			// 입금금액이 양수인지 판단
			if (moneyVerify(depositMoneyTF)) {} else {return;}
			
			try {
				// 마이너스 계좌 생성
				mainFrame.accountDao.create(new MinusAccount(accountNumTF.getText(), accountOwnerTF.getText(),
						Integer.parseInt(accountPwTF.getText()), Long.parseLong(depositMoneyTF.getText()),
						Long.parseLong(loanMoneyTF.getText())));
				
				// 정상적인 등록완료시 출력
				allet.setText("등록 완료");
				allet.setForeground(Color.GREEN);	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Actionlistener에 따라 accounts의 계좌를 삭제하는 메소드
	 */
	private void removeAccount() {
		try {
			if(mainFrame.accountDao.remove(accountNumTF.getText())) {
				// 정상적인 삭제 완료시 출력
				allet.setText("삭제 완료");
				allet.setForeground(Color.GREEN);	
			}
		} catch (IOException e1) {
			// 삭제 실패시 출력
			e1.printStackTrace();
			allet.setText("삭제 실패");
			allet.setForeground(Color.RED);	
		}

	}
}
