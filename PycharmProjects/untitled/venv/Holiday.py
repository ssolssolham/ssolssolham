import datetime
import requests

class Holiday:
    def __init__(self, solar_holiday=None, luna_holiday=None):
        self._holiday = []
        self._solar_holiday = ['0101', '0301', '0505', '0606', '0815', '1003', '1009', '1225'] if solar_holiday is None else solar_holiday
        self._luna_holiday = ['0101', '0102', '0408', '0814', '0815', '0816'] if luna_holiday is None else luna_holiday
        self.__solar_to_luna_url= 'https://astro.kasi.re.kr/life/solc'
        self.__luna_to_solar_url = 'https://astro.kasi.re.kr/life/lunc'
        self.__params = {'yyyy':'1900', 'mm':'01', 'dd':'01'}
        self.__dt = datetime

    def get_luna_day(self, year, month, day):
        self.__params['yyyy'] = year
        self.__params['mm'] = month
        self.__params['dd'] = day

        luna_date = requests.get(self.__solar_to_luna_url, params=self.__params).json()

        return {'year':luna_date['LUNC_YYYY'], 'month':luna_date['LUNC_MM'], 'day':luna_date['LUNC_DD']}

    def set_solar_holiday(self, year, holidays):
        for holiday in holidays:
            str_list = []
            str_list.append(year)
            str_list.append(holiday)
            self._holiday.append(''.join(str_list))

        self._holiday.sort()

    def set_luna_holiday(self, year, holidays):
        for holiday in holidays:
            self.__params['yyyy'] = year
            self.__params['mm'] = holiday[0:2]
            self.__params['dd'] = holiday[2:]

            # 음력으로 받아올때는 list를 받아오는데 이건 평달, 윤달때문에 그렇다..
            # 2020년 4월이 윤달이다.
            solar_date = requests.get(self.__luna_to_solar_url, params=self.__params).json()
            print(solar_date)
            str_list = []
            str_list.append(solar_date['SOLC_YYYY'])
            str_list.append(solar_date['SOLC_MM'])
            str_list.append(solar_date['SOLC_DD'])

            self._holiday.append(''.join(str_list))

            if holiday == '0101':
                # 양력으로 된 음력 1월 1일 바로 전날 구하기
                last_day_luna = datetime.datetime(solar_date['SOLC_YYYY'], solar_date['SOLC_MM'], solar_date['SOLC_DD']) - datetime.timedelta(days=1)
                str_list = []
                str_list.append(str(last_day_luna.year))
                str_list.append(str(last_day_luna.month).zfill(2)) # zfill을 통해 2자리 문자열로 바꿔준다. zero padding
                str_list.append(str(last_day_luna.day).zfill(2)) # zfill을 통해 2자리 문자열로 바꿔준다. zero padding

                self._holiday.append(''.join(str_list))

            self._holiday.sort()

    def is_holiday(self, year, month, day):
        in_date = self.__dt.datetime(int(year), int(month), int(day))

        if in_date.weekday() == 6 or in_date.weekday() == 7:
            return True

        date = self.get_luna_day(year, month, day)
        mmdd = date['month'] + date['day']

        if month+day in self._solar_holiday:
            return True

        # 음력 1월 1일 이전 날짜인 경우에 휴일이므로 음력 12월은 29일이나 30일이 마지막이므로
        # 하루 더해서 음력 1월 1일이면 휴일
        if mmdd == '1229' or mmdd == '1230':
            next_day = in_date + self.__dt.timedelta(days=1)
            next_luna_day = self.get_luna_day(str(next_day.year), str(next_day.month).zfill(2), str(next_day.day).zfill(2))
            if next_luna_day['month'] + next_luna_day['day'] == '0101':
                return True

        if mmdd in self._luna_holiday:
            return True

        return False