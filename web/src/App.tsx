import React, { useState, useEffect } from 'react';
import { 
  Train, 
  Search, 
  ArrowRight, 
  RefreshCw, 
  Clock, 
  CheckCircle2, 
  AlertCircle, 
  Zap, 
  Sparkles, 
  Send, 
  Globe, 
  User, 
  MapPin, 
  ChevronRight,
  ShieldCheck
} from 'lucide-react';
import { STATIONS, TRAINS_SAMPLE, SPLIT_OPTIONS, PNR_DEMOS, Language } from './data';

export default function App() {
  const [lang, setLang] = useState<Language>('bn');
  const [tab, setTab] = useState<'search' | 'pnr' | 'live' | 'ai'>('search');
  const [istTime, setIstTime] = useState<string>('');

  // Search state
  const [fromCode, setFromCode] = useState('HWH');
  const [toCode, setToCode] = useState('NDLS');
  const [selectedSplit, setSelectedSplit] = useState<any | null>(null);

  // PNR state
  const [pnrInput, setPnrInput] = useState('6428190342');
  const [pnrResult, setPnrResult] = useState<any | null>(PNR_DEMOS['6428190342']);

  // Live Tracking state
  const [trackedTrain, setTrackedTrain] = useState('12301');

  // AI Chat state
  const [chatMessages, setChatMessages] = useState<Array<{ sender: 'user' | 'ai', text: string }>>([
    {
      sender: 'ai',
      text: 'নমস্কার! আমি আপনার ভারতীয় রেল এআই সহায়ক ও ট্রাভেল প্ল্যানার। স্প্লিট টিকিট, ট্রেনের সময়সূচী (IST), পিএনআর কনফার্মেশন প্রেডিকশন বা লাইভ লোকেশন নিয়ে যেকোনো প্রশ্ন করুন।'
    }
  ]);
  const [inputPrompt, setInputPrompt] = useState('');

  // IST live clock
  useEffect(() => {
    const updateTime = () => {
      const now = new Date();
      const options: Intl.DateTimeFormatOptions = {
        timeZone: 'Asia/Kolkata',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: true
      };
      setIstTime(new Intl.DateTimeFormat('en-US', options).format(now) + ' IST');
    };
    updateTime();
    const interval = setInterval(updateTime, 1000);
    return () => clearInterval(interval);
  }, []);

  const handlePnrSearch = (code?: string) => {
    const query = code || pnrInput;
    if (PNR_DEMOS[query]) {
      setPnrResult(PNR_DEMOS[query]);
    } else {
      setPnrResult({
        pnr: query,
        train: '12301 Howrah Rajdhani Express',
        date: '28 Sep 2026',
        from: 'Howrah (HWH)',
        to: 'New Delhi (NDLS)',
        dep: '04:50 PM IST',
        arr: '10:05 AM IST',
        chart: 'Chart Not Prepared',
        chartBn: 'চার্ট তৈরি হয়নি',
        prob: 88,
        levelBn: 'খুব বেশি সম্ভাবনা (High)',
        levelHi: 'उच्च संभावना (High)',
        levelEn: 'High Probability (88%)',
        passengers: [
          { name: 'Passenger 1', booking: 'GNWL 22', current: 'WL 6', coach: 'WL', berth: '6' }
        ],
        adviceBn: 'কনফার্মেশন সম্ভাবনা ৮৮%। চার্ট তৈরির সময় নিশ্চিত আসন পাওয়ার জোরালো আশা রয়েছে।',
        adviceHi: 'कन्फर्म होने की 88% संभावना है। चार्ट बनने तक सीट कन्फर्म हो सकती है।',
        adviceEn: 'Confirmation probability is 88%. Strong chance of confirmation upon charting.'
      });
    }
  };

  const handleSendAi = (text?: string) => {
    const q = text || inputPrompt;
    if (!q.trim()) return;

    const newMsgs = [...chatMessages, { sender: 'user' as const, text: q }];
    setChatMessages(newMsgs);
    setInputPrompt('');

    setTimeout(() => {
      let reply = '';
      const lower = q.toLowerCase();
      if (lower.includes('স্প্লিট') || lower.includes('split') || lower.includes('সিট')) {
        reply = lang === 'bn' 
          ? 'রেডরেল প্রযুক্তির মতো আমাদের স্প্লিট-টিকিট সমাধান আপনাকে ওয়েটিংলিস্ট এড়িয়ে নিশ্চিত সিট পেতে সাহায্য করে! যেমন ১২৩০১ ট্রেনে হাওড়া থেকে DDU পর্যন্ত 2A এবং DDU থেকে দিল্লি পর্যন্ত 1A কাটলে কোনো ট্রেন বদল ছাড়াই ১০০% কনফার্ম বার্থ পাবেন।'
          : lang === 'hi'
          ? 'स्प्लिट-टिकट प्रणाली में मध्यवर्ती स्टेशन पर सीट बदलकर 100% कन्फर्म सीट मिलती है। जैसे हावड़ा से DDU तक 2A और DDU से नई दिल्ली तक 1A लेकर बिना ट्रेन बदले यात्रा करें।'
          : 'Split-ticketing allows you to get 100% confirmed berths by switching seats/classes at intermediate stations (e.g. 2A to 1A at DDU on Train 12301) without changing trains!';
      } else if (lower.includes('pnr') || lower.includes('পিএনআর')) {
        reply = lang === 'bn'
          ? 'পিএনআর কনফার্মেশন প্রেডিকশন ঐতিহাসিক চার্টিং ডেটা বিশ্লেষণ করে। প্রথম চার্ট ট্রেন ছাড়ার ৪ ঘণ্টা আগে এবং চূড়ান্ত চার্ট ৩০ মিনিট আগে (IST) তৈরি হয়।'
          : lang === 'hi'
          ? 'पीएनआर कन्फर्मेशन भविष्यवाणी ऐतिहासिक रुझानों पर आधारित होती है। पहला चार्ट ट्रेन छूटने से 4 घंटे पहले (IST) तैयार होता है।'
          : 'PNR predictions analyze historical trends. The first chart is prepared 4 hours before departure (IST) and the final chart 30 mins before.';
      } else {
        reply = lang === 'bn'
          ? 'ভারতীয় রেল ভ্রমণ সংক্রান্ত যেকোনো তথ্য, তৎকাল টিকিট সময়সূচী (সকাল ১০:০০ ও ১১:০০ AM IST) ও লাইভ রানিং স্ট্যাটাসে আমি আপনাকে সাহায্য করতে প্রস্তুত।'
          : lang === 'hi'
          ? 'भारतीय रेल यात्रा, तत्काल बुकिंग समय (10:00 AM और 11:00 AM IST) एवं लाइव स्टेटस हेतु मैं आपकी सहायता के लिए तैयार हूँ।'
          : 'I am here to assist with Indian Railways schedules strictly in IST, Tatkal rules, and live tracking updates.';
      }
      setChatMessages(prev => [...prev, { sender: 'ai', text: reply }]);
    }, 400);
  };

  const getStationName = (code: string) => {
    const s = STATIONS.find(x => x.code === code);
    if (!s) return code;
    return lang === 'bn' ? s.nameBn : lang === 'hi' ? s.nameHi : s.nameEn;
  };

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      {/* Top Header */}
      <header className="bg-gradient-to-r from-blue-900 to-indigo-950 text-white shadow-lg sticky top-0 z-50">
        <div className="max-w-5xl mx-auto px-4 py-3 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="bg-amber-500 p-2 rounded-xl text-white shadow">
              <Train className="w-6 h-6" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h1 className="font-bold text-lg md:text-xl tracking-tight">RailSathi</h1>
                <span className="text-xs bg-blue-800/80 text-amber-300 px-2 py-0.5 rounded-full font-medium">Redrail AI</span>
              </div>
              <p className="text-xs text-blue-200">
                {lang === 'bn' ? 'ভারতীয় রেল ভ্রমণ সহায়ক ও স্প্লিট-টিকিট' : lang === 'hi' ? 'भारतीय रेल यात्रा सहायक एवं स्प्लिट-टिकट' : 'Indian Railways AI Travel & Split-Ticket Planner'}
              </p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            {/* Live IST clock */}
            <div className="hidden sm:flex items-center gap-1.5 bg-white/10 backdrop-blur px-3 py-1.5 rounded-full text-xs font-semibold text-amber-300 border border-white/10">
              <Clock className="w-3.5 h-3.5" />
              <span>{istTime || 'IST Time'}</span>
            </div>

            {/* Language Switcher */}
            <div className="flex items-center bg-white/15 backdrop-blur p-0.5 rounded-lg border border-white/20 text-xs font-medium">
              <button 
                onClick={() => setLang('bn')} 
                className={`px-2.5 py-1 rounded-md transition ${lang === 'bn' ? 'bg-amber-400 text-blue-950 font-bold shadow' : 'text-white hover:text-amber-200'}`}
              >
                বাংলা
              </button>
              <button 
                onClick={() => setLang('hi')} 
                className={`px-2.5 py-1 rounded-md transition ${lang === 'hi' ? 'bg-amber-400 text-blue-950 font-bold shadow' : 'text-white hover:text-amber-200'}`}
              >
                हिन्दी
              </button>
              <button 
                onClick={() => setLang('en')} 
                className={`px-2.5 py-1 rounded-md transition ${lang === 'en' ? 'bg-amber-400 text-blue-950 font-bold shadow' : 'text-white hover:text-amber-200'}`}
              >
                English
              </button>
            </div>
          </div>
        </div>

        {/* Tab Navigation */}
        <div className="max-w-5xl mx-auto px-4 flex border-t border-white/10 overflow-x-auto text-sm">
          {[
            { id: 'search', labelBn: 'ট্রেন ও স্প্লিট', labelHi: 'ट्रेन व स्प्लिट', labelEn: 'Trains & Split' },
            { id: 'pnr', labelBn: 'পিএনআর স্টেটাস', labelHi: 'पीएनआर स्थिति', labelEn: 'PNR Status' },
            { id: 'live', labelBn: 'লাইভ ট্র্যাকিং (IST)', labelHi: 'लाइव ट्रैकिंग (IST)', labelEn: 'Live Tracking (IST)' },
            { id: 'ai', labelBn: 'রেল এআই সহায়ক', labelHi: 'रेल एआई सहायक', labelEn: 'Rail AI Assistant' }
          ].map(t => (
            <button
              key={t.id}
              onClick={() => setTab(t.id as any)}
              className={`py-3 px-4 border-b-2 font-medium transition whitespace-nowrap flex items-center gap-1.5 ${
                tab === t.id ? 'border-amber-400 text-amber-300 bg-white/5' : 'border-transparent text-slate-300 hover:text-white'
              }`}
            >
              {lang === 'bn' ? t.labelBn : lang === 'hi' ? t.labelHi : t.labelEn}
            </button>
          ))}
        </div>
      </header>

      {/* Main Container */}
      <main className="max-w-5xl mx-auto px-4 py-6 flex-1 w-full">
        {/* Tab 1: Train Search & Split Tickets */}
        {tab === 'search' && (
          <div className="space-y-6">
            {/* Search Box */}
            <div className="bg-white rounded-2xl shadow-sm border border-slate-200 p-5">
              <h2 className="text-lg font-bold text-slate-800 mb-4 flex items-center gap-2">
                <Search className="w-5 h-5 text-blue-700" />
                {lang === 'bn' ? 'ট্রেন অনুসন্ধান ও আসন প্রাপ্যতা' : lang === 'hi' ? 'ट्रेन खोज एवं सीट उपलब्धता' : 'Train Search & Seat Availability'}
              </h2>

              <div className="grid grid-cols-1 md:grid-cols-3 gap-4 items-center">
                <div>
                  <label className="text-xs font-semibold text-slate-500 uppercase tracking-wider block mb-1">
                    {lang === 'bn' ? 'কোথা থেকে (From)' : lang === 'hi' ? 'कहाँ से (From)' : 'From Station'}
                  </label>
                  <select 
                    value={fromCode} 
                    onChange={e => setFromCode(e.target.value)} 
                    className="w-full bg-slate-50 border border-slate-300 rounded-xl px-3 py-2.5 font-semibold text-slate-800 focus:outline-none focus:ring-2 focus:ring-blue-600"
                  >
                    {STATIONS.map(s => (
                      <option key={s.code} value={s.code}>
                        {lang === 'bn' ? s.nameBn : lang === 'hi' ? s.nameHi : s.nameEn} ({s.code})
                      </option>
                    ))}
                  </select>
                </div>

                <div>
                  <label className="text-xs font-semibold text-slate-500 uppercase tracking-wider block mb-1">
                    {lang === 'bn' ? 'কোথায় যাবেন (To)' : lang === 'hi' ? 'कहाँ तक (To)' : 'To Station'}
                  </label>
                  <select 
                    value={toCode} 
                    onChange={e => setToCode(e.target.value)} 
                    className="w-full bg-slate-50 border border-slate-300 rounded-xl px-3 py-2.5 font-semibold text-slate-800 focus:outline-none focus:ring-2 focus:ring-blue-600"
                  >
                    {STATIONS.map(s => (
                      <option key={s.code} value={s.code}>
                        {lang === 'bn' ? s.nameBn : lang === 'hi' ? s.nameHi : s.nameEn} ({s.code})
                      </option>
                    ))}
                  </select>
                </div>

                <div className="md:pt-5">
                  <button className="w-full bg-blue-700 hover:bg-blue-800 text-white font-bold py-2.5 px-4 rounded-xl shadow transition flex items-center justify-center gap-2">
                    <Search className="w-4 h-4" />
                    <span>{lang === 'bn' ? 'ট্রেন ও স্প্লিট খুঁজুন' : lang === 'hi' ? 'ट्रेन व स्प्लिट खोजें' : 'Search Trains'}</span>
                  </button>
                </div>
              </div>
            </div>

            {/* Split-Ticket Highlight (Redrail Feature) */}
            <div className="bg-gradient-to-r from-amber-50 to-orange-50 border border-amber-200 rounded-2xl p-5 shadow-sm">
              <div className="flex items-start gap-3">
                <div className="bg-amber-500 text-white p-2 rounded-xl mt-0.5">
                  <Zap className="w-5 h-5" />
                </div>
                <div className="flex-1">
                  <div className="flex items-center gap-2 mb-1">
                    <h3 className="font-bold text-amber-950 text-base">
                      {lang === 'bn' ? 'রেডরেল স্প্লিট-টিকিট সমাধান (Seat-Switching)' : lang === 'hi' ? 'रेडरेल स्प्लिट-टिकट समाधान (Seat-Switching)' : 'Redrail Split-Ticket Seat-Switching Solution'}
                    </h3>
                    <span className="bg-emerald-600 text-white text-xs px-2 py-0.5 rounded-full font-bold">100% CNF</span>
                  </div>
                  <p className="text-xs text-amber-900 mb-3">
                    {lang === 'bn' 
                      ? 'সরাসরি টিকিট ওয়েটিংলিস্টে থাকলে মধ্যবর্তী স্টেশনে আসন পরিবর্তন করে ১০০% নিশ্চিত সিট পান!'
                      : lang === 'hi'
                      ? 'सीधा टिकट वेटिंग लिस्ट में होने पर मध्यवर्ती स्टेशन पर सीट बदलकर 100% कन्फर्म सीट पाएं!'
                      : 'Get 100% confirmed seats by switching seats at an intermediate station when direct seats are full!'}
                  </p>

                  {SPLIT_OPTIONS.map(opt => (
                    <div key={opt.id} className="bg-white rounded-xl p-4 border border-amber-200 shadow-sm space-y-3">
                      <div className="flex flex-wrap items-center justify-between gap-2 border-b border-slate-100 pb-2">
                        <span className="font-bold text-slate-800 text-sm">
                          {lang === 'bn' ? opt.trainNameBn : lang === 'hi' ? opt.trainNameHi : opt.trainNameEn}
                        </span>
                        <span className="text-blue-800 font-extrabold text-sm">
                          {lang === 'bn' ? 'মোট ভাড়া:' : lang === 'hi' ? 'कुल किराया:' : 'Total Fare:'} ₹{opt.totalFare}
                        </span>
                      </div>

                      <div className="grid grid-cols-1 md:grid-cols-2 gap-3 text-xs">
                        <div className="bg-slate-50 p-2.5 rounded-lg border border-slate-200">
                          <span className="bg-blue-100 text-blue-800 font-bold px-1.5 py-0.5 rounded text-[10px] uppercase">Part 1</span>
                          <p className="font-semibold text-slate-700 mt-1">{opt.seg1.from} ➜ {opt.seg1.to} ({opt.seg1.class})</p>
                          <p className="text-slate-500">{opt.seg1.seat} • {opt.seg1.dep} - {opt.seg1.arr}</p>
                          <p className="text-slate-600 font-medium">₹{opt.seg1.fare}</p>
                        </div>
                        <div className="bg-slate-50 p-2.5 rounded-lg border border-slate-200">
                          <span className="bg-blue-100 text-blue-800 font-bold px-1.5 py-0.5 rounded text-[10px] uppercase">Part 2</span>
                          <p className="font-semibold text-slate-700 mt-1">{opt.seg2.from} ➜ {opt.seg2.to} ({opt.seg2.class})</p>
                          <p className="text-slate-500">{opt.seg2.seat} • {opt.seg2.dep} - {opt.seg2.arr}</p>
                          <p className="text-slate-600 font-medium">₹{opt.seg2.fare}</p>
                        </div>
                      </div>

                      <div className="flex items-center justify-between pt-1">
                        <p className="text-xs text-amber-800 font-medium">
                          {lang === 'bn' ? opt.noteBn : lang === 'hi' ? opt.noteHi : opt.noteEn}
                        </p>
                        <button 
                          onClick={() => setSelectedSplit(opt)}
                          className="text-xs font-bold text-blue-700 hover:text-blue-900 bg-blue-50 hover:bg-blue-100 px-3 py-1.5 rounded-lg transition"
                        >
                          {lang === 'bn' ? 'বিস্তারিত দেখুন' : lang === 'hi' ? 'विवरण देखें' : 'View Details'}
                        </button>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>

            {/* Direct Trains List */}
            <div className="space-y-4">
              <h3 className="font-bold text-slate-700 text-sm">
                {lang === 'bn' ? 'সরাসরি উপলব্ধ ট্রেনসমূহ:' : lang === 'hi' ? 'सीधी उपलब्ध ट्रेनें:' : 'Direct Available Trains:'}
              </h3>

              {TRAINS_SAMPLE.map(t => (
                <div key={t.number} className="bg-white rounded-2xl p-5 shadow-sm border border-slate-200 space-y-3">
                  <div className="flex flex-wrap items-center justify-between gap-2">
                    <div>
                      <h4 className="font-bold text-slate-800 text-base">
                        {t.number} - {lang === 'bn' ? t.nameBn : lang === 'hi' ? t.nameHi : t.nameEn}
                      </h4>
                      <p className="text-xs text-slate-500">{t.type} • Daily</p>
                    </div>
                    <span className="text-xs bg-slate-100 text-slate-700 font-semibold px-2.5 py-1 rounded-full">
                      {t.duration}
                    </span>
                  </div>

                  <div className="flex items-center justify-between text-xs text-slate-600 py-2 border-y border-slate-100">
                    <div>
                      <p className="font-bold text-sm text-blue-900">{t.dep}</p>
                      <p className="text-slate-500">{getStationName(fromCode)}</p>
                    </div>
                    <ArrowRight className="w-4 h-4 text-slate-400" />
                    <div className="text-right">
                      <p className="font-bold text-sm text-blue-900">{t.arr}</p>
                      <p className="text-slate-500">{getStationName(toCode)}</p>
                    </div>
                  </div>

                  <div className="flex flex-wrap gap-2 pt-1">
                    {t.classes.map(c => (
                      <div 
                        key={c.code} 
                        className={`px-3 py-2 rounded-xl text-xs font-medium border flex-1 min-w-[90px] text-center ${
                          c.available 
                            ? 'bg-emerald-50 border-emerald-200 text-emerald-800' 
                            : 'bg-red-50 border-red-200 text-red-800'
                        }`}
                      >
                        <p className="font-bold">{c.code}</p>
                        <p className="text-[11px] font-semibold">{c.status}</p>
                        <p className="text-[10px] text-slate-500 font-normal">₹{c.fare}</p>
                      </div>
                    ))}
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* Tab 2: PNR Status & Prediction */}
        {tab === 'pnr' && (
          <div className="space-y-6">
            <div className="bg-white rounded-2xl shadow-sm border border-slate-200 p-5">
              <h2 className="text-lg font-bold text-slate-800 mb-2">
                {lang === 'bn' ? 'পিএনআর স্টেটাস ও সম্ভাবনা প্রেডিকশন' : lang === 'hi' ? 'पीएनआर स्थिति एवं कन्फर्मेशन भविष्यवाणी' : 'PNR Status & Confirmation Prediction'}
              </h2>
              <p className="text-xs text-slate-500 mb-4">
                {lang === 'bn' ? '১০ সংখ্যার পিএনআর দিয়ে তাৎক্ষণিক কনফার্মেশন প্রবাবিলিটি ও চার্ট স্টেটাস দেখুন।' : lang === 'hi' ? '10 अंकों का पीएनआर दर्ज कर कन्फर्मेशन प्रतिशत जानें।' : 'Enter 10-digit PNR to check real-time confirmation chance.'}
              </p>

              <div className="flex gap-2 mb-3">
                <input 
                  type="text" 
                  value={pnrInput} 
                  onChange={e => setPnrInput(e.target.value)} 
                  placeholder="Enter 10-digit PNR" 
                  className="flex-1 bg-slate-50 border border-slate-300 rounded-xl px-4 py-2.5 font-bold tracking-wider text-slate-800 focus:outline-none focus:ring-2 focus:ring-blue-600"
                />
                <button 
                  onClick={() => handlePnrSearch()}
                  className="bg-blue-700 hover:bg-blue-800 text-white font-bold px-5 py-2.5 rounded-xl shadow transition"
                >
                  {lang === 'bn' ? 'যাচাই করুন' : lang === 'hi' ? 'जांचें' : 'Check'}
                </button>
              </div>

              <div className="flex flex-wrap items-center gap-2 text-xs text-slate-500">
                <span>{lang === 'bn' ? 'নমুনা PNR:' : lang === 'hi' ? 'नमूना PNR:' : 'Demo PNRs:'}</span>
                <button onClick={() => { setPnrInput('6428190342'); handlePnrSearch('6428190342'); }} className="bg-slate-100 hover:bg-slate-200 px-2 py-1 rounded font-mono">
                  6428190342 (WL ➜ 94%)
                </button>
                <button onClick={() => { setPnrInput('2841957201'); handlePnrSearch('2841957201'); }} className="bg-slate-100 hover:bg-slate-200 px-2 py-1 rounded font-mono">
                  2841957201 (RAC ➜ 98%)
                </button>
              </div>
            </div>

            {pnrResult && (
              <div className="bg-white rounded-2xl shadow-sm border border-slate-200 p-5 space-y-4">
                <div className="flex flex-wrap items-center justify-between gap-2 border-b border-slate-100 pb-3">
                  <div>
                    <span className="text-xs font-semibold text-slate-400 uppercase">PNR: {pnrResult.pnr}</span>
                    <h3 className="font-bold text-slate-800 text-base">{pnrResult.train}</h3>
                    <p className="text-xs text-slate-500">{pnrResult.date} • {pnrResult.from} ➜ {pnrResult.to}</p>
                  </div>
                  <span className="bg-blue-50 text-blue-800 font-bold px-3 py-1 rounded-lg text-xs">
                    {lang === 'bn' ? pnrResult.chartBn : pnrResult.chart}
                  </span>
                </div>

                {/* Probability Bar */}
                <div className="bg-emerald-50 border border-emerald-200 rounded-xl p-4">
                  <div className="flex items-center justify-between mb-2">
                    <span className="text-xs font-bold text-emerald-900">
                      {lang === 'bn' ? 'কনফার্মেশন সম্ভাবনা:' : lang === 'hi' ? 'कन्फर्म होने की संभावना:' : 'Confirmation Probability:'}
                    </span>
                    <span className="text-lg font-extrabold text-emerald-800">{pnrResult.prob}%</span>
                  </div>
                  <div className="w-full bg-emerald-200 rounded-full h-2.5 overflow-hidden">
                    <div className="bg-emerald-600 h-2.5 rounded-full" style={{ width: `${pnrResult.prob}%` }}></div>
                  </div>
                  <p className="text-xs text-emerald-800 mt-2 font-medium">
                    {lang === 'bn' ? pnrResult.adviceBn : lang === 'hi' ? pnrResult.adviceHi : pnrResult.adviceEn}
                  </p>
                </div>

                {/* Passengers */}
                <div className="space-y-2">
                  <h4 className="text-xs font-bold text-slate-500 uppercase tracking-wider">
                    {lang === 'bn' ? 'যাত্রী বিবরণ' : lang === 'hi' ? 'यात्री विवरण' : 'Passenger Details'}
                  </h4>
                  {pnrResult.passengers.map((p: any, idx: number) => (
                    <div key={idx} className="flex items-center justify-between p-3 bg-slate-50 rounded-xl border border-slate-200 text-xs">
                      <div>
                        <p className="font-bold text-slate-800">{p.name}</p>
                        <p className="text-slate-500">Booking: {p.booking}</p>
                      </div>
                      <div className="text-right">
                        <span className="bg-amber-100 text-amber-900 font-bold px-2 py-0.5 rounded text-[11px]">
                          Current: {p.current}
                        </span>
                        <p className="text-slate-500 text-[10px] mt-0.5">Coach: {p.coach} | Berth: {p.berth}</p>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        )}

        {/* Tab 3: Live Running Tracking */}
        {tab === 'live' && (
          <div className="space-y-6">
            <div className="bg-white rounded-2xl shadow-sm border border-slate-200 p-5">
              <h2 className="text-lg font-bold text-slate-800 mb-2">
                {lang === 'bn' ? 'লাইভ ট্রেন রানিং স্ট্যাটাস (IST)' : lang === 'hi' ? 'लाइव ट्रेन रनिंग स्थिति (IST)' : 'Live Train Running Status (IST)'}
              </h2>
              <div className="flex gap-2">
                <button 
                  onClick={() => setTrackedTrain('12301')} 
                  className={`px-3 py-1.5 rounded-lg text-xs font-semibold ${trackedTrain === '12301' ? 'bg-blue-700 text-white' : 'bg-slate-100 text-slate-700'}`}
                >
                  12301 Rajdhani
                </button>
                <button 
                  onClick={() => setTrackedTrain('22301')} 
                  className={`px-3 py-1.5 rounded-lg text-xs font-semibold ${trackedTrain === '22301' ? 'bg-blue-700 text-white' : 'bg-slate-100 text-slate-700'}`}
                >
                  22301 Vande Bharat
                </button>
              </div>
            </div>

            <div className="bg-white rounded-2xl shadow-sm border border-slate-200 p-5 space-y-4">
              <div className="flex flex-wrap items-center justify-between gap-2 border-b border-slate-100 pb-3">
                <div>
                  <h3 className="font-bold text-slate-800 text-base">
                    {trackedTrain === '12301' ? '12301 Howrah - New Delhi Rajdhani Express' : '22301 Howrah - NJP Vande Bharat Express'}
                  </h3>
                  <p className="text-xs text-slate-500">Live IST Telemetry • Updated at {istTime}</p>
                </div>
                <span className="bg-emerald-50 text-emerald-800 border border-emerald-200 font-bold px-3 py-1 rounded-full text-xs">
                  {lang === 'bn' ? 'সঠিক সময়ে চলছে (Right Time)' : lang === 'hi' ? 'समय पर चल रही है (Right Time)' : 'Running on Time'}
                </span>
              </div>

              <div className="grid grid-cols-2 md:grid-cols-3 gap-3 text-xs">
                <div className="bg-slate-50 p-3 rounded-xl border border-slate-200">
                  <span className="text-slate-400 block mb-0.5">Current Station</span>
                  <span className="font-bold text-slate-800 text-sm">
                    {trackedTrain === '12301' ? 'Pt. Deen Dayal Upadhyaya (DDU)' : 'Malda Town (MLDT)'}
                  </span>
                </div>
                <div className="bg-slate-50 p-3 rounded-xl border border-slate-200">
                  <span className="text-slate-400 block mb-0.5">Speed</span>
                  <span className="font-bold text-blue-700 text-sm">
                    {trackedTrain === '12301' ? '0 km/h (Halting)' : '124 km/h'}
                  </span>
                </div>
                <div className="bg-slate-50 p-3 rounded-xl border border-slate-200">
                  <span className="text-slate-400 block mb-0.5">Next Station</span>
                  <span className="font-bold text-slate-800 text-sm">
                    {trackedTrain === '12301' ? 'Prayagraj (PRYJ)' : 'Barsoi Jn (BOE)'}
                  </span>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* Tab 4: AI Railway Travel Planner */}
        {tab === 'ai' && (
          <div className="bg-white rounded-2xl shadow-sm border border-slate-200 flex flex-col h-[560px] overflow-hidden">
            <div className="bg-slate-100 px-4 py-3 border-b border-slate-200 flex items-center justify-between">
              <div className="flex items-center gap-2">
                <Sparkles className="w-5 h-5 text-amber-500" />
                <h3 className="font-bold text-slate-800 text-sm">
                  {lang === 'bn' ? 'ভারতীয় রেলওয়ে বহুভাষিক এআই সহায়ক' : lang === 'hi' ? 'भारतीय रेल बहुभाषी एआई सहायक' : 'Indian Railways Multilingual AI Planner'}
                </h3>
              </div>
              <span className="text-xs text-slate-500 font-mono">IST strictly synchronized</span>
            </div>

            {/* Quick Suggestions */}
            <div className="p-3 bg-slate-50 border-b border-slate-200 flex gap-2 overflow-x-auto text-xs">
              {[
                { bn: 'স্প্লিট টিকিট কীভাবে কাজ করে?', hi: 'स्प्लिट टिकट कैसे काम करता है?', en: 'How does split ticketing work?' },
                { bn: 'হাওড়া থেকে দিল্লি ভ্রমণের পরিকল্পনা', hi: 'हावड़ा से दिल्ली यात्रा योजना', en: 'Howrah to Delhi travel plan' },
                { bn: 'তৎকাল টিকিটের সময়সূচী (IST)', hi: 'तत्काल टिकट समय सारिणी (IST)', en: 'Tatkal ticket timings (IST)' }
              ].map((s, i) => (
                <button
                  key={i}
                  onClick={() => handleSendAi(lang === 'bn' ? s.bn : lang === 'hi' ? s.hi : s.en)}
                  className="bg-white hover:bg-slate-100 border border-slate-200 px-3 py-1 rounded-full whitespace-nowrap text-slate-700 transition"
                >
                  {lang === 'bn' ? s.bn : lang === 'hi' ? s.hi : s.en}
                </button>
              ))}
            </div>

            {/* Messages Area */}
            <div className="flex-1 p-4 overflow-y-auto space-y-3">
              {chatMessages.map((m, idx) => (
                <div 
                  key={idx} 
                  className={`flex ${m.sender === 'user' ? 'justify-end' : 'justify-start'}`}
                >
                  <div 
                    className={`max-w-[85%] rounded-2xl p-3.5 text-xs leading-relaxed ${
                      m.sender === 'user' 
                        ? 'bg-blue-700 text-white rounded-br-none' 
                        : 'bg-slate-100 text-slate-800 rounded-bl-none border border-slate-200'
                    }`}
                  >
                    {m.text}
                  </div>
                </div>
              ))}
            </div>

            {/* Input Bar */}
            <div className="p-3 bg-slate-50 border-t border-slate-200 flex gap-2">
              <input 
                type="text" 
                value={inputPrompt} 
                onChange={e => setInputPrompt(e.target.value)} 
                onKeyDown={e => e.key === 'Enter' && handleSendAi()}
                placeholder={lang === 'bn' ? 'যেকোনো প্রশ্ন লিখুন...' : lang === 'hi' ? 'कोई भी प्रश्न लिखें...' : 'Ask any railway question...'}
                className="flex-1 bg-white border border-slate-300 rounded-xl px-4 py-2 text-xs text-slate-800 focus:outline-none focus:ring-2 focus:ring-blue-600"
              />
              <button 
                onClick={() => handleSendAi()}
                className="bg-blue-700 hover:bg-blue-800 text-white p-2.5 rounded-xl transition"
              >
                <Send className="w-4 h-4" />
              </button>
            </div>
          </div>
        )}
      </main>

      {/* Split Details Modal */}
      {selectedSplit && (
        <div className="fixed inset-0 bg-black/50 backdrop-blur-sm z-50 flex items-center justify-center p-4">
          <div className="bg-white rounded-2xl max-w-lg w-full p-6 space-y-4 shadow-xl">
            <div className="flex items-center justify-between border-b border-slate-100 pb-3">
              <h3 className="font-bold text-slate-800 text-base">
                {lang === 'bn' ? 'স্প্লিট-টিকিট সমাধান বিবরণ' : lang === 'hi' ? 'स्प्लिट-टिकट विवरण' : 'Split-Ticket Details'}
              </h3>
              <button onClick={() => setSelectedSplit(null)} className="text-slate-400 hover:text-slate-600 text-sm">✕</button>
            </div>

            <div className="space-y-3 text-xs">
              <div className="bg-emerald-50 border border-emerald-200 text-emerald-900 p-3 rounded-xl font-bold flex items-center gap-2">
                <CheckCircle2 className="w-5 h-5 text-emerald-600 shrink-0" />
                <span>100% Confirmed Berth Guarantee via Seat-Switching</span>
              </div>

              <div className="p-3 bg-slate-50 rounded-xl border border-slate-200 space-y-1">
                <span className="font-bold text-blue-900 block">Segment 1: {selectedSplit.seg1.from} ➜ {selectedSplit.seg1.to}</span>
                <p className="text-slate-600">{selectedSplit.seg1.seat} • Dep: {selectedSplit.seg1.dep} | Arr: {selectedSplit.seg1.arr}</p>
                <p className="font-semibold text-slate-800">Fare: ₹{selectedSplit.seg1.fare}</p>
              </div>

              <div className="text-center font-bold text-amber-700 text-xs py-1">
                ⬇ Switch Coach & Berth at {selectedSplit.intermediate} (Halt: {selectedSplit.haltMinutes}m IST)
              </div>

              <div className="p-3 bg-slate-50 rounded-xl border border-slate-200 space-y-1">
                <span className="font-bold text-blue-900 block">Segment 2: {selectedSplit.seg2.from} ➜ {selectedSplit.seg2.to}</span>
                <p className="text-slate-600">{selectedSplit.seg2.seat} • Dep: {selectedSplit.seg2.dep} | Arr: {selectedSplit.seg2.arr}</p>
                <p className="font-semibold text-slate-800">Fare: ₹{selectedSplit.seg2.fare}</p>
              </div>

              <div className="border-t border-slate-200 pt-2 flex justify-between font-bold text-sm text-slate-800">
                <span>Total Fare:</span>
                <span className="text-blue-800">₹{selectedSplit.totalFare}</span>
              </div>
            </div>

            <button 
              onClick={() => setSelectedSplit(null)}
              className="w-full bg-blue-700 text-white font-bold py-2 rounded-xl"
            >
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
